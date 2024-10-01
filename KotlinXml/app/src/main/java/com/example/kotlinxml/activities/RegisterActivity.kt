package com.example.kotlinxml.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinxml.R
import com.example.kotlinxml.auth.FirebaseAuthentication
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var fullName : TextInputEditText
    private lateinit var phoneNumber : TextInputEditText
    private lateinit var address : TextInputEditText
    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var btnRegister : TextView
    private lateinit var auth : FirebaseAuthentication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }
    fun init() {
        fullName = findViewById(R.id.full_name)
        phoneNumber = findViewById(R.id.phone_number)
        address = findViewById(R.id.address)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btnRegister = findViewById(R.id.tv_register)
        auth = FirebaseAuthentication()
        btnRegister.setOnClickListener {
            registerNewUser(fullName.text.toString(), phoneNumber.text.toString(), address.text.toString(), email.text.toString(), password.text.toString())
        }
    }
    fun registerNewUser(fullName:String, phoneNumber:String, address:String, email:String, password:String) {
        auth.register(email, password, fullName, phoneNumber, address) { result ->
            if (result) {
                // Đăng ký thành công
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                // Đăng ký không thành công
                Toast.makeText(this, "Đăng ký không thành công!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}