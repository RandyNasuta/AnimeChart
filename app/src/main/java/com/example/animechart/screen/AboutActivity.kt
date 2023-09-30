package com.example.animechart.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.animechart.R
import com.example.animechart.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(applicationContext)
            .load(R.drawable.foto_profil)
            .circleCrop()
            .into(binding.imgEditor)
    }
}