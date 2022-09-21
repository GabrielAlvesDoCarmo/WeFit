package com.gdsdesenvolvimento.wefit.ui.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.gdsdesenvolvimento.wefit.databinding.RvItemFavoriteBinding

class FavoriteAdapter(
    private val listItems: List<InfoRepo>,
    private val fragment : Fragment
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    inner class FavoriteViewHolder(val binding: RvItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            RvItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val infoRepo = listItems[position]
        favoriteBindInView(holder, infoRepo)
    }

    private fun favoriteBindInView(
        holder: FavoriteViewHolder,
        infoRepo: InfoRepo
    ) {
        holder.binding.textNameUser.text = infoRepo.fullName
        holder.binding.textDescription.text = infoRepo.description
        Glide.with(holder.itemView.context).load(infoRepo.ownerAvatarUrl).into(holder.binding.profileImage)
        holder.binding.numberFavorite.text = infoRepo.stargazersCount.toString()
        holder.binding.languageName.text = defineLanguage(infoRepo)
        holder.binding.root.setOnClickListener {
            openWebPage(infoRepo.htmlUrl)
        }
    }

    private fun defineLanguage(item: InfoRepo): String {
        return item.language ?: "Não Encontrado"

    }

    private fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(fragment.requireActivity().packageManager) != null) {
            fragment.startActivity(intent)
        }
    }
}