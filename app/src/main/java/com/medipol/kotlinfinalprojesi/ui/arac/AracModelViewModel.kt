package com.medipol.kotlinfinalprojesi.ui.arac

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.medipol.kotlinfinalprojesi.data.AracModelItem
import com.medipol.kotlinfinalprojesi.data.arac.AracModelRepository
import com.medipol.kotlinfinalprojesi.utils.ResourceStatus
import kotlinx.coroutines.launch

class AracModelViewModel: ViewModel() {
    private  val modelRepository: AracModelRepository = AracModelRepository()

    init {
        modelleriGetir()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var modelLiveData = MutableLiveData<List<AracModelItem>>()
    var error =    MutableLiveData<Throwable>()


    fun modelleriGetir()  = viewModelScope.launch {

        modelRepository.modelleriGetir()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("baran",it.data.toString())
                        modelLiveData.postValue(it.data!!)
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