package com.medipol.kotlinfinalprojesi.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.medipol.kotlinfinalprojesi.R
import com.medipol.kotlinfinalprojesi.databinding.ActivityLoginBinding
import com.medipol.kotlinfinalprojesi.ui.kategori.KategoriListesiActivity


class LoginActivity : AppCompatActivity(){
    private lateinit var binding:ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()


        binding.girisYapBtn.setOnClickListener {
            if(binding.loginMailEdt.text.trim().toString().isNotEmpty() && binding.loginSifreEdt.text.trim().toString().isNotEmpty()){
                loginUser(binding.loginMailEdt.text.trim().toString(),binding.loginSifreEdt.text.trim().toString())
            }else{
                Toast.makeText(this,resources.getString(R.string.login_bos_kontrolu),Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun loginUser(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Toast.makeText(this,resources.getString(R.string.login_onay_giris),Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, KategoriListesiActivity::class.java))
                }
                else{
                    Toast.makeText(this,resources.getString(R.string.login_red_giris),Toast.LENGTH_SHORT).show()
                }
            }
    }
}