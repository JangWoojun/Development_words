package com.example.developmentwords.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.developmentwords.databinding.ListItemBinding

class EnglishWordAdapter (private val englishwords : List<Word>) :
    RecyclerView.Adapter<EnglishWordAdapter.EnglishWordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnglishWordViewHolder {
        return EnglishWordViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EnglishWordViewHolder, position: Int) {
        holder.bind(englishwords[position])
    }

    override fun getItemCount(): Int {
        return englishwords.size
    }

    class EnglishWordViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(englishword : Word){
                binding.listItemText1.text = englishword.word
            }
        }

}