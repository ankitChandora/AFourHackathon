package com.example.afourhackathon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afourhackathon.databinding.ActivityHomeBinding
import com.example.afourhackathon.ui.gift.GiftsFragment
import com.example.afourhackathon.util.EventObserver
import com.google.android.material.divider.MaterialDividerItemDecoration

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchPosts()

        viewModel.posts.observe(this) {
            adapter = PostAdapter(it, object : PostAdapter.OnItemClickListener {
                override fun openGiftScreen() {
                    GiftsFragment.show(supportFragmentManager)
                }
            })

            binding.rvPosts.layoutManager = LinearLayoutManager(this)
            val dividerItemDecoration = MaterialDividerItemDecoration(
                this,
                MaterialDividerItemDecoration.VERTICAL
            )
            dividerItemDecoration.isLastItemDecorated = false
            binding.rvPosts.addItemDecoration(dividerItemDecoration)
            binding.rvPosts.adapter = adapter
        }

        viewModel.showToast.observe(this, EventObserver {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }
}