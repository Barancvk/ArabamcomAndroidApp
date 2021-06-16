package com.medipol.kotlinfinalprojesi.data

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName


@Keep
@IgnoreExtraProperties
class AracModelItem{

    @PropertyName("AracDetayImgURL")
    val AracDetayImgURL: String?=null

    @PropertyName("AracIsmi")
    val AracIsmi: String?=null

    @PropertyName("AracKategoriBilgisi")
    val AracKategoriBilgisi: String?=null

    @PropertyName("AracModel")
    val AracModel: String?=null

    @PropertyName("DetayAciklama")
    val DetayAciklama: String?=null

    @PropertyName("Yil")
    val Yil: String?=null



    override fun toString(): String {
        return "KategoriItem(AracDetayImgURL=$AracDetayImgURL, AracIsmi=$AracIsmi, AracKategoriBilgisi=$AracKategoriBilgisi, AracModel=$AracModel, DetayAciklama=$DetayAciklama, Yil=$Yil)"
    }
}
