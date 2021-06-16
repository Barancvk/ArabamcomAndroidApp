package com.medipol.kotlinfinalprojesi.data

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName


@Keep
@IgnoreExtraProperties
class MarkaItem{

    @PropertyName("imageUrl")
    val imageUrl: String?=null

    @PropertyName("kategoriIsmi")
    val kategoriIsmi: String?=null


    override fun toString(): String {
        return "KategoriItem(imageUrl=$imageUrl, kategoriIsmi=$kategoriIsmi)"
    }
}

