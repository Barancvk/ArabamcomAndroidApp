package com.medipol.kotlinfinalprojesi.ui.detay

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.medipol.kotlinfinalprojesi.databinding.ActivityAracDetayBinding
import com.medipol.kotlinfinalprojesi.utils.Constants

class AracDetayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAracDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAracDetayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    fun init(){
        binding.detayAracAciklamaTxt.text=Constants.detayAciklama
        binding.detayAracModelTxt.text=Constants.modelIsmi
        binding.detayAracYilTxt.text=Constants.aracYil
        binding.detayAracAciklamaTxt.text=Constants.detayAciklama
        val imageBytes = Base64.decode(Constants.detayImg, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        binding.detayImg.setImageBitmap(decodedImage)
    }

}