package com.example.animechart.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animechart.R
import com.example.animechart.adapter.ListAnimeAdapter
import com.example.animechart.databinding.ActivityMainBinding
import com.example.animechart.model.Anime
import com.example.animechart.database.AnimeList
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val tag = "main_activity"
    private lateinit var binding: ActivityMainBinding
    private var animeDB: AnimeList = AnimeList()
    private var animeList: ArrayList<Anime> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        try {
            animeList = animeDB.getDataAnime()
        } catch (e: Exception) {
            Log.e(tag, "onCreate: ${e.message}")
        }

        binding.rvAnimes.setHasFixedSize(true)
        binding.rvAnimes.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(animeList)
        binding.rvAnimes.adapter = listAnimeAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAbout -> {
                val intent = Intent(applicationContext, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}