package com.example.jajanyuk.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jajanyuk.R
import com.example.jajanyuk.data.model.response.pembeli.DataDetailPedagang
import com.example.jajanyuk.data.model.response.pembeli.DataItemPedagangNerby
import com.example.jajanyuk.databinding.ItemDetailProdukBinding
import com.example.jajanyuk.databinding.ItemProdukBinding
import com.example.jajanyuk.ui.pembeli.DetailPedagangActivity

class DetailProdukAdapter(private val clickListener: DetailProdukClickListener): ListAdapter<DataDetailPedagang, DetailProdukAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemDetailProdukBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataDetailPedagang, clickListener: DetailProdukClickListener) = with(binding) {
            tvTitle.text = data.products?.get(0)?.name ?: ""
            tvNama.text = data.products?.get(0)?.description ?: ""
            tvHarga.text = (data.products?.get(0)?.price ?: 0).toString()

            Glide.with(itemView.context)
                .load(data.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(ivGambar)

            btnMinus.setOnClickListener {
                clickListener.onMinusButtonClick(adapterPosition)
            }

            btnPlus.setOnClickListener {
                clickListener.onPlusButtonClick(adapterPosition)
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it,clickListener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemDetailProdukBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    interface DetailProdukClickListener {
        fun onMinusButtonClick(position: Int)
        fun onPlusButtonClick(position: Int)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataDetailPedagang>() {
            override fun areItemsTheSame(oldItem:DataDetailPedagang, newItem: DataDetailPedagang): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataDetailPedagang, newItem:DataDetailPedagang ): Boolean {
                return oldItem == newItem
            }
        }
    }
}