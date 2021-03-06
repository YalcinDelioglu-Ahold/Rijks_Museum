package com.myd.rijksmuseum.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myd.rijksmuseum.databinding.CollectionsItemBinding
import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.presentation.di.FragmentScope
import com.myd.rijksmuseum.presentation.fragments.CollectionsFragmentDirections
import javax.inject.Inject

@FragmentScope
class CollectionsAdapter @Inject internal constructor() :
    ListAdapter<Collection, CollectionsAdapter.CollectionItemViewHolder>(
        CollectionDiffCallback()
    ) {

    /**
     * Item click listener
     */
    var onItemClickListener: ((Collection) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionItemViewHolder =
        CollectionItemViewHolder(
            CollectionsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CollectionItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CollectionItemViewHolder(
        private val binding: CollectionsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Collection) {
            binding.apply {
                collection = item

                itemView.setOnClickListener {
                    onItemClickListener?.let { listener ->
                        listener(item)
                    }
                }
            }
        }
    }
}

private class CollectionDiffCallback : DiffUtil.ItemCallback<Collection>() {
    override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
        return oldItem.id == newItem.id
                && oldItem.objectNumber == newItem.objectNumber
    }

    override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
        return oldItem.objectNumber == newItem.objectNumber
                && oldItem.principalMaker == newItem.principalMaker
                && oldItem.imageUrl == newItem.imageUrl
    }
}