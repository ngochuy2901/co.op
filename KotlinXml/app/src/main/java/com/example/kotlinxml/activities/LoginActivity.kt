package com.example.kotlinxml.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinxml.MainActivity
import com.example.kotlinxml.R
import com.example.kotlinxml.auth.FirebaseAuthentication
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var btnLogin : Button
    private lateinit var tvResetPassword : TextView
    private lateinit var tvRegister : TextView
    private lateinit var auth : FirebaseAuthentication
    private lateinit var backBtn : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        init()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        email = findViewById(R.id.edittext_email)
        password = findViewById(R.id.edittext_password)
        tvRegister = findViewById(R.id.tv_register)
        tvResetPassword = findViewById(R.id.tv_reset_password)
        btnLogin = findViewById(R.id.btn_login)
        auth = FirebaseAuthentication()
        backBtn = findViewById(R.id.back_button)
        backBtn.setOnClickListener(View.OnClickListener {
            finish()
        })
        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        tvResetPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
        btnLogin.setOnClickListener(View.OnClickListener {
            auth.login(email.text.toString(), password.text.toString()) { result->
                if (result) {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}