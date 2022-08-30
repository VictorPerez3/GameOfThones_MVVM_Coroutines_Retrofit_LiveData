package com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.model.Got
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.databinding.AdapterGotBinding
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.util.ValidationUtil

class GotAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var gotList = mutableListOf<Got>()

    fun setGot(gots: List<Got>) {
        this.gotList = gots.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterGotBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val got = gotList[position]
        if (ValidationUtil.validateGot(got)) {
            holder.binding.name.text = got.fullName
            Glide.with(holder.itemView.context).load(got.imageUrl).into(holder.binding.imageview)
        }
    }

    override fun getItemCount(): Int {
        return gotList.size
    }
}

class MainViewHolder(val binding: AdapterGotBinding) : RecyclerView.ViewHolder(binding.root) {

}