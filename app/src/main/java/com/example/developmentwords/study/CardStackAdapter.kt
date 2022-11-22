package com.example.developmentwords.study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.developmentwords.databinding.CardItemBinding
import com.example.developmentwords.recyclerview.Voca

class CardStackAdapter(private val items:List<Voca>) :
    RecyclerView.Adapter<CardStackAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int) : CardViewHolder{
        val binding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return CardViewHolder(binding).also {
            binding.cardItem.setOnClickListener {
                if (binding.wordText.visibility == View.VISIBLE){
                    binding.wordText.visibility = View.INVISIBLE
                    binding.meanText.visibility = View.VISIBLE
                    binding.touchText.text = "터치하여 단어 확인"
                }
                else {
                    binding.wordText.visibility = View.VISIBLE
                    binding.meanText.visibility = View.INVISIBLE
                    binding.touchText.text = "터치하여 뜻 확인"
                }
            }
        }
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CardViewHolder(private val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:Voca){
            binding.wordText.text = data.word
            binding.meanText.text = data.mean
        }
    }

}