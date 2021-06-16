package com.medipol.kotlinfinalprojesi.adapter

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medipol.kotlinfinalprojesi.data.AracModelItem
import com.medipol.kotlinfinalprojesi.data.ItemClickListener
import com.medipol.kotlinfinalprojesi.databinding.AracModelCardviewBinding
import com.medipol.kotlinfinalprojesi.utils.Constants

class AracModelAdapter (var modeller:ArrayList<AracModelItem>, var onItemClickListener: ItemClickListener): RecyclerView.Adapter<AracModelAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AracModelCardviewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AracModelCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return modeller.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {
                binding.aracIsimTxt.text=modeller[position].AracIsmi
                binding.aracModelTxt.text=modeller[position].AracModel
                binding.aracYilTxt.text=modeller[position].Yil
                val imageBytes = Base64.decode(modeller[position].AracDetayImgURL, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                binding.aracImg.setImageBitmap(decodedImage)
                aracModelleriListelemeCardView.setOnClickListener{

                    Constants.model = modeller[position].AracIsmi
                    Constants.modelIsmi = modeller[position].AracModel
                    Constants.aracYil = modeller[position].Yil
                    Constants.detayAciklama = modeller[position].DetayAciklama
                    Constants.detayImg = modeller[position].AracDetayImgURL

                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
}
