package com.example.salon

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.salon.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var firebaseAuth: FirebaseAuth

    companion object{
        var a=0
        var uid:String? = null
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressDialog = ProgressDialog(this)
        firebaseAuth= FirebaseAuth.getInstance()

        if (a==0){
            oqibolish()
        }

        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Kuting...")
        binding.button2.setOnClickListener{
            progressDialog.show()
            firebaseAuth.signInWithEmailAndPassword(
                binding.edittext1.text.toString(),
                binding.edittext2.text.toString()
            ).addOnCompleteListener(
                {

                    if (it.isSuccessful){
                        Toast.makeText(this,firebaseAuth.currentUser!!.uid,Toast.LENGTH_SHORT).show();
                        uid=firebaseAuth.currentUser!!.uid
                        progressDialog.hide()
                        saqlash()
                        startActivity(Intent(this,MainActivity3::class.java))
                    }
                })
        }
    }

    fun saqlash(){
        sharedPreferences=getPreferences(MODE_PRIVATE)
        editor= sharedPreferences.edit()
        editor.putString("abs123", uid)
        editor.commit()
    }
    fun oqibolish(){
        sharedPreferences=getPreferences(MODE_PRIVATE)
        uid= sharedPreferences.getString("abs123",null).toString()
        if (uid!=null){
            startActivity(Intent(this,MainActivity3::class.java))
        }
    }


}