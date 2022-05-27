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
import com.andreispanait.beers.database.model.BeerAndIngredients
import com.andreispanait.beers.databinding.ListItemBeerBinding


class BeersAdapter(
    private val clickListener: BeersListener
) :
    ListAdapter<BeerAndIngredients, BeersAdapter.BeerViewHolder>(object :
        DiffUtil.ItemCallback<BeerAndIngredients>() {
        override fun areItemsTheSame(
            oldItem: BeerAndIngredients,
            newItem: BeerAndIngredients
        ): Boolean {
            return oldItem.beer.id == newItem.beer.id
        }

        override fun areContentsTheSame(
            oldItem: BeerAndIngredients,
            newItem: BeerAndIngredients
        ): Boolean {
            return oldItem.beer.image == newItem.beer.image && oldItem.beer.name == newItem.beer.name
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
            val beerAndIngredients = getItem(position)
            root.setOnClickListener { clickListener.beerClick(beerAndIngredients) }
            image.load(beerAndIngredients.beer.image)
            name.text = beerAndIngredients.beer.name
        }
    }

    inner class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}