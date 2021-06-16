package com.medipol.kotlinfinalprojesi.data.arac

import com.medipol.kotlinfinalprojesi.data.AracModelItem
import com.medipol.kotlinfinalprojesi.utils.Resource
import kotlinx.coroutines.flow.Flow

class AracModelRepository {
    private var modelDataSource: AracModelDataSource?=null

    init {
        modelDataSource= AracModelFirebaseDataSource()
    }

    fun modelleriGetir(): Flow<Resource<List<AracModelItem>>>
    {
        return modelDataSource!!.modelleriGetir()
    }

}