package com.example.animechart.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animechart.databinding.ItemAnimeRowBinding
import com.example.animechart.model.Anime
import com.example.animechart.screen.DetailActivity

class ListAnimeAdapter(private val listAnime: ArrayList<Anime>): RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder>() {
    private val tag = "list_anime_adapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAnimeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, name, synopsis, photo) = listAnime[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.binding.imgAnime)
        holder.binding.txtAnimeTitle.text = name
        holder.binding.txtAnimeDescription.text = synopsis
        holder.itemView.setOnClickListener {
            Log.i(tag, "onBindViewHolder: Berpindah Halaman")
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ANIME, listAnime[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listAnime.size

    class ListViewHolder(var binding: ItemAnimeRowBinding): RecyclerView.ViewHolder(binding.root)
}