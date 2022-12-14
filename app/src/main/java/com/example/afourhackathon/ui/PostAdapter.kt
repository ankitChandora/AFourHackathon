package com.example.afourhackathon.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.afourhackathon.R
import com.example.afourhackathon.data.model.Post
import com.example.afourhackathon.databinding.ItemPostBinding
import com.example.afourhackathon.databinding.LayoutItemPostGiftBinding
import com.example.afourhackathon.util.DataUtil

/**
 * Created by ChandoraAnkit on 16/11/22
 */
class PostAdapter(
    private val posts: List<Post>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val s = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostImageViewHolder(s, clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostImageViewHolder) {
            holder.bind(posts[position])
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }


    class PostImageViewHolder(
        private val binding: ItemPostBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {

            post.user?.let {
                binding.tvUserName.text = it.name
                binding.tvUserBio.text = it.bio

                binding.ivUserProfile.load(it.profileImg) {
                    placeholder(R.drawable.profile_placeholder)
                    transformations(CircleCropTransformation())
                }
            }

            binding.ivPost.load(post.postUrl) {
                placeholder(R.drawable.post_placeholder)
            }

            binding.tvCaption.text = post.caption
            binding.tvViews.text = "${post.views} Views"
            binding.tvShares.text = post.shares
            binding.tvComments.text = post.comments
            binding.tvLikes.text = post.likes

            binding.rlGift.setOnClickListener {
                listener.openGiftScreen()
            }

            post.gifts?.let { gifts ->
                binding.llGifts.visibility = View.VISIBLE
                binding.llGifts.removeAllViews()
                gifts.forEach { gift ->
                    val giftView = LayoutItemPostGiftBinding.inflate(LayoutInflater.from(binding.root.context), binding.llGifts, false)
                    val lottieFile = DataUtil.GIFTS[gift.assetId]
                    if (lottieFile != null) {
                        giftView.lavGift.setAnimation(lottieFile)
                    }
                    giftView.tvCount.text = gift.count.toString()
                    binding.llGifts.addView(giftView.llItemGift)
                }

            } ?: kotlin.run {
                binding.llGifts.visibility = View.GONE
            }
        }

    }

    interface OnItemClickListener {
        fun openGiftScreen()
    }

}