package com.gdsdesenvolvimento.wefit.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApi
import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApiItem
import com.gdsdesenvolvimento.wefit.databinding.RvItemBinding

class HomeAdapter(
    private val listItems: ResponseApi
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
        bindingItemInView(item,holder.binding)
    }

    private fun bindingItemInView(item: ResponseApiItem, binding: RvItemBinding) {

    }
}