package com.medipol.kotlinfinalprojesi.ui.arac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.medipol.kotlinfinalprojesi.adapter.AracModelAdapter
import com.medipol.kotlinfinalprojesi.data.*
import com.medipol.kotlinfinalprojesi.databinding.ActivityAracModelleriListelemeBinding
import com.medipol.kotlinfinalprojesi.ui.detay.AracDetayActivity

class AracModelleriListelemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAracModelleriListelemeBinding
    var aracModelViewModel = AracModelViewModel()
    private lateinit var aracModelAdapter: AracModelAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAracModelleriListelemeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.progressBarAracListeleme.visibility = View.VISIBLE
        init()
    }

    private fun init() {
        modelleriAl()

    }

    fun modelleriListele(modeller: List<AracModelItem>) {
        aracModelAdapter = AracModelAdapter(modeller as ArrayList<AracModelItem>, object : ItemClickListener {
            override fun onDelete(position: Int) {
            }

            override fun onItemClick(position: Int) {
                startActivity(Intent(this@AracModelleriListelemeActivity, AracDetayActivity::class.java))
            }
        })



        binding.aracModelListelemeRecyclerView.adapter = aracModelAdapter
        binding.aracModelListelemeRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.progressBarAracListeleme.visibility = View.GONE

        binding.defaultSiralamaBtn.setOnClickListener(View.OnClickListener { view ->
            binding.defaultSiralamaBtn.isEnabled = false
            binding.gridSiralamaBtn.isEnabled = true

            binding.aracModelListelemeRecyclerView.adapter = aracModelAdapter
            binding.aracModelListelemeRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        })
        binding.gridSiralamaBtn.setOnClickListener(View.OnClickListener { view ->
            binding.gridSiralamaBtn.isEnabled = false
            binding.defaultSiralamaBtn.isEnabled = true

            binding.aracModelListelemeRecyclerView.adapter = aracModelAdapter
            binding.aracModelListelemeRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        })
    }

    fun modelleriAl() {

        aracModelViewModel.apply {
            modelLiveData.observe(this@AracModelleriListelemeActivity, Observer {

                modelleriListele(it)



            })
            error.observe(this@AracModelleriListelemeActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
            loading?.observe(this@AracModelleriListelemeActivity, Observer {
            })


        }


    }
}