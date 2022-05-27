package com.andreispanait.beers.ui.beers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.database.model.BeerAndIngredients
import com.andreispanait.beers.databinding.FragmentBeersBinding

import com.andreispanait.beers.utils.OperationResult
import com.andreispanait.beers.viewmodel.BeersViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeersFragment : Fragment(), BeersListener {

    private var _binding: FragmentBeersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BeersViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BeersAdapter(this)
        binding.beers.adapter = adapter
        lifecycleScope.launch {
            viewModel.beers.flowWithLifecycle(lifecycle).collect { result ->
                when (result) {
                    is OperationResult.Loading -> binding.loader.isVisible = true
                    is OperationResult.Success -> {
                        adapter.submitList(result.data)
                        binding.loader.isGone = true
                    }
                    is OperationResult.Error -> Snackbar.make(
                        binding.root,
                        result.message ?: "Error",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun beerClick(beerAndIngredients: BeerAndIngredients) {
        findNavController().navigate(
            BeersFragmentDirections.actionBeersToBeerDetails(
                beerAndIngredients,
                beerAndIngredients.beer.name
            )
        )
    }

}