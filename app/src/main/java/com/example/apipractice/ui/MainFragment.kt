package com.example.apipractice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apipractice.data.datasource.impl.RemoteDataSource
import com.example.apipractice.data.repository.WineRepository
import com.example.apipractice.databinding.FragmentMainBinding
import com.example.apipractice.domain.usecase.fetchwinelist.FetchWineListUseCase
import com.example.apipractice.ui.uistate.SyncListUiState
import com.example.apipractice.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private val wineListAdapter = WineListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataSource = RemoteDataSource()
        val wineRepository = WineRepository(dataSource)
        viewModel = ViewModelProvider(
            this,
            MainViewModel.Factory(FetchWineListUseCase(wineRepository))
        )[MainViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wineListItemUiState.collect { uiState ->
                    wineListAdapter.submitList(uiState)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.syncListUiState.collect {
                    binding.swipeRefreshLayout.isRefreshing = it.isRefreshing

                    when(it) {
                        SyncListUiState.Success -> {
                            Snackbar.make(
                                binding.root,
                                "Successful!",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        SyncListUiState.Failure -> {
                            Snackbar.make(
                                binding.root,
                                "Failed.",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        else -> {
                            // noop
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wineListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.wineListRecyclerView.adapter = wineListAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchWineList()
        }

        viewModel.fetchWineList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}