package com.medipol.kotlinfinalprojesi.data.marka



import com.medipol.kotlinfinalprojesi.data.MarkaItem
import com.medipol.kotlinfinalprojesi.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MarkaDataSource {
    fun markalariGetir(): Flow<Resource<List<MarkaItem>>>
}