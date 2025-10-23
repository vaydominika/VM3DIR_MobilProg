package com.vm3dir_mobilprog_catshelter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vm3dir_mobilprog_catshelter.data.Cat
import com.vm3dir_mobilprog_catshelter.repository.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatViewModel(private val repository: CatRepository) : ViewModel() {
    
    private val _availableCats = MutableStateFlow<List<Cat>>(emptyList())
    val availableCats: StateFlow<List<Cat>> = _availableCats.asStateFlow()
    
    private val _adoptedCats = MutableStateFlow<List<Cat>>(emptyList())
    val adoptedCats: StateFlow<List<Cat>> = _adoptedCats.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadCats()
    }
    
    private fun loadCats() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllAvailableCats().collect { cats ->
                _availableCats.value = cats
                _isLoading.value = false
            }
        }
        
        viewModelScope.launch {
            repository.getAllAdoptedCats().collect { cats ->
                _adoptedCats.value = cats
            }
        }
    }
    
    fun addCat(cat: Cat) {
        viewModelScope.launch {
            repository.insertCat(cat)
        }
    }
    
    fun updateCat(cat: Cat) {
        viewModelScope.launch {
            repository.updateCat(cat)
        }
    }
    
    fun deleteCat(cat: Cat) {
        viewModelScope.launch {
            repository.deleteCat(cat)
        }
    }
    
    fun adoptCat(catId: Long) {
        viewModelScope.launch {
            repository.adoptCat(catId)
        }
    }
    
    fun makeCatAvailable(catId: Long) {
        viewModelScope.launch {
            repository.makeCatAvailable(catId)
        }
    }
}

