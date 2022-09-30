package com.example.aagniyaproject

import android.R.id.text2
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.RED
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.aagniyaproject.databinding.ActivityRegisterBinding
import com.example.aagniyaproject.user.*

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var myViewModel: MyViewModel

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myRepository = MyRepository(MyDataBase.getDatabase(this))
        val factory = MyFactory(myRepository)
        myViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]

        binding.signIn.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        binding.backArrow.setOnClickListener {
            onBackPressed()
        }

        val spannableString = SpannableString(binding.termsTitle.text.toString())
        val orange = ForegroundColorSpan(Color.GREEN)
        spannableString.setSpan(orange,
            48, 60, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.termsTitle.text = spannableString

        val spannableStringBuilder = SpannableStringBuilder(binding.termsTitle.text)
        val blue = ForegroundColorSpan(Color.GREEN )
        spannableStringBuilder.setSpan(blue,
            65, 79, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.termsTitle.text = spannableStringBuilder

        registerValidation()
    }

    private fun Context.showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun registerValidation() {
        binding.apply {
            register.setOnClickListener {
                when {
                    userName.text.toString().isEmpty() -> {
                        showToast("Enter Email Address")
                    }
                    email.text.toString().isEmpty() -> {
                        showToast("Enter Email Address")
                    }
                    password.text.toString().isEmpty() -> {
                        showToast("Enter Email Address")
                    }
                    confirmPassword.text.toString().isEmpty() -> {
                        showToast("Enter  Password")
                    }

                    else -> {

                        lifecycleScope.launchWhenStarted {
                            val user_name = userName.text.toString().trim()
                            val email = email.text.toString().trim()
                            val password = password.text.toString().trim()
                            val confirm_password = confirmPassword.text.toString().trim()
//
                            myViewModel.findByEmail(email)!!.collect {
                                Log.i("TAG", "registerValidation:$it ")
                                if (it == null) {
                                    myViewModel.register(
                                        MyTable(
                                            user_name,
                                            email,
                                            password,
                                            confirm_password)
                                    )

                                    showToast(" Register Successfully ")
                                    binding.userName.setText("")
                                    binding.email.setText("")
                                    binding.password.setText("")
                                    binding.confirmPassword.setText("")
                                    val intent = Intent(this@Register, Login::class.java)
                                    startActivity(intent)
                                } else {
                                    showToast("Email Already Register ")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}