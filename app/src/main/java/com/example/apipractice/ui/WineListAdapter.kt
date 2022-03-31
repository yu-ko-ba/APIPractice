package com.example.apipractice.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apipractice.R
import com.example.apipractice.databinding.WineListItemBinding
import com.example.apipractice.ui.uistate.WineListItemUiState

class WineListAdapter : ListAdapter<WineListItemUiState, WineListAdapter.ViewHolder>(WineListCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wineNameText = view.findViewById<TextView>(R.id.wineNameText)
    }

    internal class WineListCallback : DiffUtil.ItemCallback<WineListItemUiState>() {
        override fun areItemsTheSame(oldItem: WineListItemUiState, newItem: WineListItemUiState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WineListItemUiState,
            newItem: WineListItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    private lateinit var binding: WineListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wine_list_item, parent, false)

        binding = WineListItemBinding.bind(view)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.wineNameText.text = item.nameText
    }
}