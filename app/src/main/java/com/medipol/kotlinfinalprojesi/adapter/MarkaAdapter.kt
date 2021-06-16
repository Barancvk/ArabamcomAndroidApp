package com.medipol.kotlinfinalprojesi.adapter

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medipol.kotlinfinalprojesi.data.ItemClickListener
import com.medipol.kotlinfinalprojesi.data.MarkaItem
import com.medipol.kotlinfinalprojesi.databinding.KategoriCardviewBinding
import com.medipol.kotlinfinalprojesi.utils.Constants

class MarkaAdapter (var markalar:ArrayList<MarkaItem>, var onItemClickListener: ItemClickListener):RecyclerView.Adapter<MarkaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: KategoriCardviewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = KategoriCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return markalar.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {
                binding.kategoriIsmiTxt.text=markalar[position].kategoriIsmi
                val imageBytes = Base64.decode(markalar[position].imageUrl, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                binding.kategoriListeleImg.setImageBitmap(decodedImage)
                kategoriCardview.setOnClickListener{
                    Constants.marka = markalar[position].kategoriIsmi
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
}