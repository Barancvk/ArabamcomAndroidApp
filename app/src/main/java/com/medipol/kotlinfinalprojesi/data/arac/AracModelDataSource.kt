package com.medipol.kotlinfinalprojesi.data.arac

import com.medipol.kotlinfinalprojesi.data.AracModelItem
import com.medipol.kotlinfinalprojesi.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AracModelDataSource {
    fun modelleriGetir(): Flow<Resource<List<AracModelItem>>>
}