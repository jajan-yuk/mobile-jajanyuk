package com.example.jajanyuk.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jajanyuk.R
import com.example.jajanyuk.data.model.response.pembeli.DataItemPedagangNerby
import com.example.jajanyuk.databinding.ItemProdukBinding
import com.example.jajanyuk.databinding.ItemProdukMapBinding

class ProdukMapAdapter: ListAdapter<DataItemPedagangNerby, ProdukMapAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemProdukMapBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItemPedagangNerby) = with(binding) {
            tvTitle.text = data.nameMerchant
            tvNama.text = data.summaryProductPedagang?.nameProduct ?: ""
            tvRating.text = (data.summaryProductPedagang?.rating ?: "").toString()
            tvHarga.text = (data.summaryProductPedagang?.harga ?: "").toString()



//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, DetailArticleActivity::class.java).apply {
//                    putExtra(DetailArticleActivity.STORY_INTENT_DATA, data)
//                }
//                itemView.context.startActivity(intent)
//            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemProdukMapBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItemPedagangNerby>() {
            override fun areItemsTheSame(oldItem:DataItemPedagangNerby, newItem: DataItemPedagangNerby): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataItemPedagangNerby, newItem:DataItemPedagangNerby ): Boolean {
                return oldItem == newItem
            }
        }
    }
}