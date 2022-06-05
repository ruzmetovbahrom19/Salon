package com.example.salon

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import com.example.salon.databinding.ActivityMain3Binding
import com.google.firebase.database.*
import java.sql.Time
import java.util.*
import kotlin.jvm.internal.MutablePropertyReference2

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding

    lateinit var databaseReference2: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("navbatlar")

        val databaseReference2 =FirebaseDatabase.getInstance().reference.child("Userlarim").child(MainActivity.uid!!)

        databaseReference2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var ism = snapshot.child("name").getValue() as String
                var familya = snapshot.child("surname").getValue() as String
                binding.textviewusername.setText(ism +""+ familya)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        binding.imageviewlogout.setOnClickListener {
            MainActivity.a = 1
            startActivity(Intent(this@MainActivity3, MainActivity::class.java))
        }
        binding.floatingactionbutton.setOnClickListener {
            val dialog = AlertDialog.Builder(this@MainActivity3)
            dialog.setTitle("Navbatga yozilish")
            dialog.setMessage("navbatga yozilish uchun vaqtni belgilang")
            val view1 = LayoutInflater.from(this@MainActivity3).inflate(R.layout.vaqtlayout, null)
            val buttonstart = view1.findViewById<Button>(R.id.buttonstart)
            val buttonstop = view1.findViewById<Button>(R.id.buttonstop)
            buttonstart.setOnClickListener {
                val calendar = Calendar.getInstance()
                var hour = calendar.get(Calendar.HOUR_OF_DAY)
                var minute = calendar.get(Calendar.MINUTE)

                val timeSetListener = TimePickerDialog.OnTimeSetListener { it, hour, minute ->
                    if (hour < 10) {
                        if (minute < 10) {
                            buttonstart.setText("0$hour:0$minute")
                        } else {
                            buttonstart.setText("0$hour:$minute")
                        }
                    } else {
                        if (minute < 10) {
                            buttonstart.setText("$hour:0$minute")
                        } else {
                            buttonstart.setText("$hour:$minute")
                        }
                    }
                }
                val timePickerDialog =
                    TimePickerDialog(this@MainActivity3, timeSetListener, hour, minute, true)
                timePickerDialog.show()
            }
            buttonstop.setOnClickListener {
                val calendar = Calendar.getInstance()
                var hour = calendar.get(Calendar.HOUR_OF_DAY)
                var minute = calendar.get(Calendar.MINUTE)

                val timeSetListener = TimePickerDialog.OnTimeSetListener { it, hour, minute ->
                    if (hour < 10 || minute < 10) {
                        buttonstop.setText("0$hour:0$minute")
                    }
                }
                val timePickerDialog =
                    TimePickerDialog(this@MainActivity3, timeSetListener, hour, minute, true)
                timePickerDialog.show()
            }
            dialog.setView(view1)
            dialog.create()
            dialog.show()

        }
    }
}