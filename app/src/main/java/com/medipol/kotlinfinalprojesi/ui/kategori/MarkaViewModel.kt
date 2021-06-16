package com.medipol.kotlinfinalprojesi.ui.kategori

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.medipol.kotlinfinalprojesi.data.marka.MarkaRepository
import com.medipol.kotlinfinalprojesi.data.MarkaItem
import com.medipol.kotlinfinalprojesi.utils.ResourceStatus
import kotlinx.coroutines.launch

class MarkaViewModel : ViewModel() {

    private  val markaRepository: MarkaRepository = MarkaRepository()

    init {
        markalariGetir()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var markalarLiveData = MutableLiveData<List<MarkaItem>>()
    var error =    MutableLiveData<Throwable>()


    fun markalariGetir()  = viewModelScope.launch {

        markaRepository.markalariGetir()

                .asLiveData(viewModelScope.coroutineContext).observeForever {

                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            loading?.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {
                            Log.e("baran",it.data.toString())
                            markalarLiveData.postValue(it.data!!)
                            loading?.postValue(false)
                        }

                        ResourceStatus.ERROR -> {
                            error.postValue(it.throwable!!)
                            loading?.postValue(false)
                        }
                    }
                }
    }
}

