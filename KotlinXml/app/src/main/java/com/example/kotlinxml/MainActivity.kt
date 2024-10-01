package com.example.kotlinxml

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.kotlinxml.activities.LoginActivity
import com.example.kotlinxml.auth.FirebaseAuthentication
import com.example.kotlinxml.fragments.HomeFragment
import com.example.kotlinxml.fragments.NotificationFragment
import com.example.kotlinxml.fragments.OrderFragment
import com.example.kotlinxml.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomnav : BottomNavigationView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment());
        init()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun init() {
        bottomnav = findViewById(R.id.bottom_nav) as BottomNavigationView
        bottomnav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.order -> {
                    loadFragment(OrderFragment())
                    true
                }
                R.id.profile -> {
                    val auth : FirebaseAuthentication = FirebaseAuthentication()
                    if(auth.isAuthenticated()) {
                        loadFragment(ProfileFragment())
                    } else {
                        loginOrNot()
                    }
                    true
                }
                R.id.notification -> {
                    loadFragment(NotificationFragment())
                    true
                }

                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }

    private fun loginOrNot() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Yêu cầu đăng nhập")
        builder.setMessage("Bạn cần đăng nhập tiếp tục!")
        builder.setPositiveButton("Có") { dialog: DialogInterface, which: Int ->
            // Xử lý khi người dùng chọn "Có"
            startActivity(Intent(this, LoginActivity::class.java))
        }
        builder.setNegativeButton("Không") { dialog: DialogInterface, which: Int ->
            recreate()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun loadFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}