package com.example.retrofitcallapidemo.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcallapidemo.databinding.ViewItemBinding
import com.example.retrofitcallapidemo.model.DocumentModel

class DocumentAdapter(
    private val list: List<DocumentModel>,
    private val onClick: (DocumentModel) -> Unit
) :
    RecyclerView.Adapter<DocumentAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.apply {
            tvTitle.text = currentItem.title
            rcvImages.apply {
                adapter = currentItem.images?.let { ImageAdapter(context, it) }
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
            tvPublisher.text = currentItem.publisher.name
            tvTime.text = currentItem.published_date

            layoutItem.setOnClickListener {
                onClick(currentItem)
            }
        }
    }
}