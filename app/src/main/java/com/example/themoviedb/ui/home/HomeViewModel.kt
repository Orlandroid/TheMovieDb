package com.example.themoviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Repositorio
import com.example.data.di.CoroutineDispatchers
import com.example.domain.entities.MoviesProviders
import com.example.domain.state.Result
import com.example.themoviedb.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositorio: Repositorio,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val errorNetwork = "Error verifica tu conexion"

    private val _providers = MutableLiveData<Result<MoviesProviders>>()
    val providers: LiveData<Result<MoviesProviders>>
        get() = _providers


    fun getProviders() {
        viewModelScope.launch(coroutineDispatchers.io) {
            withContext(coroutineDispatchers.main) {
                _providers.value = Result.Loading
            }
            if (!networkHelper.isNetworkConnected()) {
                withContext(coroutineDispatchers.main) {
                    _providers.value = Result.ErrorNetwork(errorNetwork)
                }
                return@launch
            }
            try {
                val response = repositorio.getProviders()
                withContext(coroutineDispatchers.main) {
                    _providers.value=Result.Success(response)
                }
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    _providers.value = Result.Error(e.message ?: "Error app")
                }
            }
        }
    }
}