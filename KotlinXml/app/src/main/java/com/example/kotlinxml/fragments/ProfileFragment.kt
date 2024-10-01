package com.example.kotlinxml.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.kotlinxml.MainActivity
import com.example.kotlinxml.R
import com.example.kotlinxml.activities.LoginActivity
import com.example.kotlinxml.auth.FirebaseAuthentication
import com.example.kotlinxml.model.User
import com.example.kotlinxml.repository.UserRepository

class ProfileFragment : Fragment() {
    private lateinit var loginText : TextView
    private lateinit var registerText : TextView
    private lateinit var userRepository: UserRepository
    private lateinit var firebaseAuthentication: FirebaseAuthentication
    private lateinit var fullName : EditText
    private lateinit var phoneNumber : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_profile, container, false)
        userRepository = UserRepository()
        firebaseAuthentication = FirebaseAuthentication()
        fullName = view.findViewById(R.id.full_name)
        loginText = view.findViewById(R.id.tv_login)
        phoneNumber = view.findViewById(R.id.phone_number)
        if (firebaseAuthentication.isAuthenticated()) {
            loginText.setText("Đăng xuất")
            loginText.setOnClickListener {
                firebaseAuthentication.logout()
                restartActivity()
            }
        } else {
            loginText.setText("Đăng nhập")
            loginText.setOnClickListener {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
        }

        val uid = firebaseAuthentication.getUid()
        if (uid != null) {
            // Người dùng đã đăng nhập, lấy thông tin người dùng
            userRepository.getUserInfo(uid) { user ->
                if (user != null) {
                    // Hiển thị tên người dùng sau khi nhận dữ liệu
                    fullName.setText(user.getFullName())
                    phoneNumber.setText(user.getPhoneNumber())
                }
            }
        } else {

        }


        return view
    }
    private fun restartActivity() {
        // Tạo intent để khởi động lại Activity
        val intent = Intent(requireActivity(), MainActivity::class.java)
        // Khởi động Activity mới
        startActivity(intent)
        // Kết thúc Activity hiện tại
        requireActivity().finish()
    }

}