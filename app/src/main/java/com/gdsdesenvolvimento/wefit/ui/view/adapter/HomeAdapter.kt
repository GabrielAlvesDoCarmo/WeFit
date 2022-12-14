package com.gdsdesenvolvimento.wefit.ui.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApi
import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApiItem
import com.gdsdesenvolvimento.wefit.databinding.CardHomeBinding
import com.gdsdesenvolvimento.wefit.util.result.RvClickItem
class HomeAdapter(
    private val listItems: ResponseApi,
    private val resultClick: RvClickItem,
    private val fragment: Fragment
) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    inner class HomeHolder(val binding: CardHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            CardHomeBinding.inflate(
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
        holder.binding.languageName.text = defineLanguage(item)
        Glide.with(holder.itemView.context).load(item.owner.avatar_url)
            .into(holder.binding.profileImage)
        holder.binding.root.setOnClickListener {
            openWebPage(item.html_url)
        }
        holder.binding.btnFavorite.setOnClickListener {
            resultClick.clickFavorite(infoRepo)
            listItems.remove(item)
            notifyDataSetChanged()
        }
    }

    private fun generateInfo(item: ResponseApiItem) =
        InfoRepo(
            id = item.id,
            fullName = item.full_name,
            description = item.description,
            ownerAvatarUrl = item.owner.avatar_url,
            stargazersCount = item.stargazers_count,
            language = defineLanguage(item),
            htmlUrl = item.html_url
        )

    private fun defineLanguage(item: ResponseApiItem): String {
        return item.language ?: "N??o Encontrado"

    }

    private fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(fragment.requireActivity().packageManager) != null) {
            fragment.startActivity(intent)
        }
    }
}

