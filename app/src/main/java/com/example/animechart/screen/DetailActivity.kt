package com.example.animechart.screen

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.animechart.database.ShareList
import com.example.animechart.databinding.ActivityDetailBinding
import com.example.animechart.model.Anime
import com.example.animechart.model.Share
import jp.wasabeef.glide.transformations.BlurTransformation
import java.lang.Exception

class DetailActivity : AppCompatActivity(){
    private val tag: String = "detail_activity"
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ANIME = "extra_anime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ANIME)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Anime>(EXTRA_ANIME)
        }

        if (anime != null){
            setShareQuantity(anime.id)
            Glide.with(applicationContext).load(anime.photo).apply(RequestOptions.bitmapTransform(BlurTransformation(10,5))).into(binding.imgThumbnailDetail)
            Glide.with(applicationContext).load(anime.photo).into(binding.imgAnimeDetail)
            binding.txtAnimeTitleDetail.movementMethod = ScrollingMovementMethod()
            binding.txtAnimeTitleDetail.text = anime.name
            binding.txtAnimeSynopsisDetail.text = anime.synopsis
        }

        binding.imgBtnShare.setOnClickListener{
            try {
                val indexList: Int = getIndexAnimeShare(anime!!.id)
                if (indexList == -1){
                    var id: Int = ShareList.shareList.size
                    ShareList.shareList.add(Share(id++, anime.id, 1))
                } else {
                    ShareList.shareList[indexList].countShare++
                }
                setShareQuantity(anime.id)
                Toast.makeText(this, "Anda membagikan ${anime.name}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e(tag, "onCreate: ${e.message}" )
            }
        }
    }

    private fun setShareQuantity(idAnime: Int) {
        try {
            val indexList: Int = getIndexAnimeShare(idAnime)

            if (indexList == -1){
                binding.txtCountShare.text= "0"
            } else {
                binding.txtCountShare.text = ShareList.shareList[indexList].countShare.toString()
            }
        } catch (e: Exception) {
            Log.e(tag, "setShareQuantity: ${e.message}" )
        }
    }

    private fun getIndexAnimeShare(idAnime: Int): Int {
        try {
            var indexList: Int = -1
            ShareList.shareList.forEachIndexed { index, element ->
                if (element.animeId == idAnime) {
                    indexList = index
                }
            }
            return indexList
        } catch (e: Exception) {
            Log.e(tag, "getIndexAnimeShare: ${e.message}" )
        }
        return  -1
    }
}