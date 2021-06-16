package com.medipol.kotlinfinalprojesi.ui.kategori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.medipol.kotlinfinalprojesi.R
import com.medipol.kotlinfinalprojesi.data.ItemClickListener
import com.medipol.kotlinfinalprojesi.adapter.MarkaAdapter
import com.medipol.kotlinfinalprojesi.data.MarkaItem
import com.medipol.kotlinfinalprojesi.databinding.ActivityKategoriListesiBinding
import com.medipol.kotlinfinalprojesi.ui.arac.AracModelleriListelemeActivity
import com.medipol.kotlinfinalprojesi.utils.AlertDialogUtil

class KategoriListesiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKategoriListesiBinding
    var markaViewModel = MarkaViewModel()
    private lateinit var markaAdapter: MarkaAdapter
    var markaListesi:List<MarkaItem>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriListesiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.progressBarKategoriListeleme.visibility=View.VISIBLE
        init()

    }
    override fun onBackPressed(){
        AlertDialogUtil.alertDialogShow(this@KategoriListesiActivity, resources.getString(R.string.kategori_uyari),
                resources.getString(R.string.kategori_uyari2),
                resources.getString(R.string.kategori_uyari_evet),
                resources.getString(R.string.kategori_uyari_hayir),
                resources.getString(R.string.kategori_activity))

    }

    fun arama(marka:String?){

        marka?.let {
            markaListesi?.let {
                var markaAra = it.filter { it.kategoriIsmi!!.contains(marka) }
                kategorileriListele(markaAra)

            }
        }

    }

    private fun init() {

        markalariAl()
        aramaYap()

    }

    fun kategorileriListele(markalar:List<MarkaItem>){

        markaAdapter= MarkaAdapter(markalar as ArrayList<MarkaItem>,object : ItemClickListener {
            override fun onDelete(position: Int) {
            }
            override fun onItemClick(position: Int) {
                startActivity(Intent(this@KategoriListesiActivity, AracModelleriListelemeActivity::class.java))
            }

        })
        binding.kategoriListelemeRecylerView.adapter = markaAdapter
        binding.kategoriListelemeRecylerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.progressBarKategoriListeleme.visibility=View.GONE
    }


    fun markalariAl(){

        markaViewModel.apply {
             markalarLiveData.observe(this@KategoriListesiActivity, Observer {

                 kategorileriListele(it)
                 markaListesi=it

            })
            error.observe(this@KategoriListesiActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
            loading?.observe(this@KategoriListesiActivity, Observer {
            })
        }

    }
    fun aramaYap(){
        binding.apply {
            binding.aramaYapSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    arama(newText)
                    return false
                }
            })
        }
    }

}