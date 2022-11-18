package com.example.developmentwords.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.developmentwords.databinding.ListItemBinding

class CsWordAdapter(private val cswords : List<Word>) : RecyclerView.Adapter<CsWordAdapter.CsWordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CsWordViewHolder {
        return CsWordViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: CsWordViewHolder, position: Int) {
        holder.bind(cswords[position])
    }

    override fun getItemCount(): Int {
        return cswords.size
    }

    class CsWordViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(csword : Word) {
            binding.listItemText1.text = csword.word
        }
    }

}