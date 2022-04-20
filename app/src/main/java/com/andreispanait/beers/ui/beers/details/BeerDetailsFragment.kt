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
        binding.image.load(args.beer.image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}