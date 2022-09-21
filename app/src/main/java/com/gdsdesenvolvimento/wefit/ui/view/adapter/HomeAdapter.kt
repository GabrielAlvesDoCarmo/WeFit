package com.gdsdesenvolvimento.wefit.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApi
import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApiItem
import com.gdsdesenvolvimento.wefit.databinding.RvItemBinding
import com.gdsdesenvolvimento.wefit.util.result.RvClickItem

class HomeAdapter(
    private val listItems: ResponseApi,
    private val resultClick: RvClickItem
) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    inner class HomeHolder(val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val item = listItems[position]
        bindingItemInView(item, holder, position)
    }

    private fun bindingItemInView(item: ResponseApiItem, holder: HomeHolder, position: Int) {
        val infoRepo = generateInfo(item)
        holder.binding.textNameUser.text = item.full_name
        holder.binding.textDescription.text = item.description
        holder.binding.numberFavorite.text = item.stargazers_count.toString()
        holder.binding.languageName.text = item.language
        Glide.with(holder.itemView.context).load(item.owner.avatar_url)
            .into(holder.binding.profileImage)
        holder.binding.root.setOnClickListener {
            resultClick.clickCard(item.html_url)
        }
        holder.binding.btnFavorite.setOnClickListener {
            resultClick.clickFavorite(infoRepo)
            notifyItemRemoved(position)
        }
    }

    private fun generateInfo(item: ResponseApiItem) =
        InfoRepo(
            fullName = item.full_name,
            description = item.description,
            ownerAvatarUrl = item.owner.avatar_url,
            stargazersCount = item.stargazers_count,
            language = item.language,
            htmlUrl = item.html_url
        )
}