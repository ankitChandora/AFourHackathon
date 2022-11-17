package com.example.afourhackathon.ui.gift

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afourhackathon.R
import com.example.afourhackathon.data.DataRepository
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

                binding.tvPrice.text = gift.coins.toString()

                if (DataRepository.IS_PREMIUM){
                    binding.tvDiscountedPrice.visibility = View.VISIBLE
                    binding.tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    binding.tvPrice.background = binding.root.context.getDrawable(R.drawable.bg_strike_text)
                    binding.tvDiscountedPrice.text = (gift.coins * 0.8).toInt().toString()
                }else{
                    binding.tvDiscountedPrice.visibility = View.GONE
                }

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