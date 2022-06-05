package com.example.salon

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.salon.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val firebaseAuth=FirebaseAuth.getInstance()
        val databaseReference= FirebaseDatabase.getInstance().reference.child("Userlarim")

        val progressDialog=ProgressDialog(this)
        progressDialog.setTitle("Please Waiting...")
        progressDialog.setMessage("Kuting...")

        binding.button2.setOnClickListener{
            progressDialog.show()
            firebaseAuth.createUserWithEmailAndPassword(binding.edittext3.text.toString(),binding.edittext4.text.toString()).addOnCompleteListener(
                {
                    if (it.isSuccessful){
                        Toast.makeText(this@MainActivity2,"Registratsiya bolding",Toast.LENGTH_SHORT).show()
                        progressDialog.hide()

                        val user=User(binding.edittext1.text.toString(),binding.edittext2.text.toString(),binding.edittext3.text.toString(),binding.edittext4.text.toString(),"${firebaseAuth.currentUser!!.uid}")
                        databaseReference.child("${firebaseAuth.currentUser!!.uid}").setValue(user)

                        startActivity(Intent(this,MainActivity::class.java))
                    }
                }
            )
        }
    }
}