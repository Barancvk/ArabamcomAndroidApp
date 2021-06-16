package com.medipol.kotlinfinalprojesi.data.marka

import com.medipol.kotlinfinalprojesi.data.MarkaItem
import com.medipol.kotlinfinalprojesi.utils.Resource
import kotlinx.coroutines.flow.Flow

class MarkaRepository {
    private var markaDataSource: MarkaDataSource?=null

    init {
        markaDataSource= MarkaFirebaseDataSource()
    }

    fun markalariGetir(): Flow<Resource<List<MarkaItem>>>
    {
        return markaDataSource!!.markalariGetir()
    }

}