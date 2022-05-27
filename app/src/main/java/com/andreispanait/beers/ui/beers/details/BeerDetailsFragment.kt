package com.andreispanait.beers.ui.beers.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.andreispanait.beers.R
import com.andreispanait.beers.database.model.Hops
import com.andreispanait.beers.database.model.Malt
import com.andreispanait.beers.databinding.FragmentBeerDetailsBinding
import com.andreispanait.beers.databinding.FragmentBeersBinding
import com.andreispanait.beers.ui.beers.BeersAdapter
import com.andreispanait.beers.utils.OperationResult
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerDetailsFragment : Fragment() {
    private var _binding: FragmentBeerDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: BeerDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeerDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val beer = args.beerAndIngredients.beer
        val ingredients = args.beerAndIngredients.ingredients
        with(binding) {
            image.load(beer.image)
            description.text = beer.description

            val malt = ingredients.malt.map(Malt::name).joinToString()
            val hops = ingredients.hops.map(Hops::name).joinToString()
            this.ingredients.text =
                getString(R.string.beer_details_ingredients, malt, hops, ingredients.yeast)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}