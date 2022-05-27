package com.andreispanait.beers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.database.model.BeerAndIngredients
import com.andreispanait.beers.repository.BeersRepository
import com.andreispanait.beers.utils.OperationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(
    beersRepository: BeersRepository
) : ViewModel() {

    val beers: Flow<OperationResult<List<BeerAndIngredients>>> = beersRepository.getBeersFromDb().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        initialValue = OperationResult.Loading()
    )


}