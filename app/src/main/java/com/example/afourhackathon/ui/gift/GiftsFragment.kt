package com.example.afourhackathon.ui.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.afourhackathon.databinding.FragmentGiftsBinding
import com.example.afourhackathon.util.EventObserver
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class GiftsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentGiftsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: GiftViewModel by viewModels()

    private lateinit var adapter: GiftsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGiftsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        viewModel.fetchGifts()

        binding.ivAddCoin.setOnClickListener {
            Toast.makeText(requireContext(), "Not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addObservers() {
        viewModel.gifts.observe(this) {
            adapter = GiftsAdapter(it, object : GiftsAdapter.OnItemClickListener {
                override fun onGiftSelected(coins: Int) {
                    viewModel.updateAvailableCoins(coins)
                }

            })

            binding.rvGifts.layoutManager = GridLayoutManager(requireActivity(), 5)
            binding.rvGifts.adapter = adapter
        }

        viewModel.availableCoins.observe(this) {
            binding.tvCoins.text = it.toString()
        }

        viewModel.showToast.observe(this, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val TAG = "GiftFragment"

        fun show(fm: FragmentManager) {
            GiftsFragment().show(fm, TAG)
        }
    }
}