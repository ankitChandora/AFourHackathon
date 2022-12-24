package com.example.afourhackathon.ui

import android.R
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afourhackathon.data.DataRepository
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
        setTitle("Sharechat Sample")
        viewModel.fetchPosts()

        // Merge from test for testing
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        DataRepository.IS_PREMIUM = !DataRepository.IS_PREMIUM

        if (DataRepository.IS_PREMIUM){
            Toast.makeText(this, "You're now premium user", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.example.afourhackathon.R.menu.app_menu, menu)
        return true
    }
}