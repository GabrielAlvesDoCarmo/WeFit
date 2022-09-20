package com.gdsdesenvolvimento.wefit.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.wefit.databinding.RvItemBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    inner class HomeHolder(private val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {

    }
}