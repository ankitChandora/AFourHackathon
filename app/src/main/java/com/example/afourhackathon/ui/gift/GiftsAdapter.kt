package com.example.afourhackathon.ui.gift

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afourhackathon.data.model.Gift
import com.example.afourhackathon.databinding.ItemGiftBinding
import com.example.afourhackathon.util.DataUtil
import kotlin.math.floor

/**
 * Created by ChandoraAnkit on 17/11/22
 */


class GiftsAdapter(
    private val gifts: List<Gift>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<GiftsAdapter.GiftViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder {
        val s = ItemGiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiftViewHolder(s, clickListener)
    }

    override fun onBindViewHolder(holder: GiftViewHolder, position: Int) {
        holder.bind(gifts[position])
    }

    override fun getItemCount(): Int {
        return gifts.size
    }


    class GiftViewHolder(
        private val binding: ItemGiftBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(gift: Gift) {

            val lottieFile = DataUtil.GIFTS[gift.assetId]
            if (lottieFile != null) {
                binding.lavGift.setAnimation(lottieFile)
            }

            if (gift.coins != null) {
                binding.tvPrice.setPaintFlags(binding.tvPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                binding.tvPrice.text = (gift.coins * 0.8).toInt().toString()

                binding.root.setOnClickListener {
                    listener.onGiftSelected(gift.coins)
                }
            }

        }
    }

    interface OnItemClickListener {
        fun onGiftSelected(coins: Int)
    }

}