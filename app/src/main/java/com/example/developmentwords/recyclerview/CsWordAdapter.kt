package com.example.developmentwords.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.developmentwords.databinding.ListItemBinding

private var chkWord : String = ""

class CsWordAdapter(private val cswords : List<Word>) : RecyclerView.Adapter<CsWordAdapter.CsWordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CsWordViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )

        return CsWordViewHolder(binding).also {

            binding.wordBox.setOnClickListener {
                if (binding.listItemText2.visibility == View.VISIBLE){
                    binding.listItemText2.visibility = View.GONE
                    binding.listItemText3.visibility = View.VISIBLE
                }
                else {
                    binding.listItemText2.visibility = View.VISIBLE
                    binding.listItemText3.visibility = View.GONE
                }
                chkWord = binding.listItemText1.text as String
            }
        }
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
            if (csword.word == chkWord){
                binding.listItemText2.visibility = View.GONE
                binding.listItemText3.visibility = View.VISIBLE
            }
            else {
                binding.listItemText2.visibility = View.VISIBLE
                binding.listItemText3.visibility = View.GONE
            }
            binding.listItemText1.text = csword.word
            binding.listItemText4.text = csword.mean
        }
    }

}