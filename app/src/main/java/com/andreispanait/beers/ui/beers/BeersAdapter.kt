package com.andreispanait.beers.ui.beers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andreispanait.beers.R
import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.databinding.ListItemBeerBinding


class BeersAdapter(
    private val clickListener: BeersListener
) :
    ListAdapter<Beer, BeersAdapter.BeerViewHolder>(object : DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.image == newItem.image && oldItem.name == newItem.name
        }

    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_beer,
                parent,
                false,

                )
        )
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        ListItemBeerBinding.bind(holder.itemView).run {
            val beer = getItem(position)
            root.setOnClickListener { clickListener.beerClick(beer) }
            image.load(beer.image)
            name.text = beer.name
        }
    }

    inner class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}