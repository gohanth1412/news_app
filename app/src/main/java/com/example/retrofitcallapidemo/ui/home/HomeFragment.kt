package com.example.retrofitcallapidemo.ui.home

import android.Manifest
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.retrofitcallapidemo.databinding.FragmentHomeBinding
import com.example.retrofitcallapidemo.ui.home.tablayout.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.INTERNET),
            0
        )
        bindView()
    }

    private fun bindView() {
        binding.viewPager.apply {
            adapter = TabAdapter(this@HomeFragment)
            isUserInputEnabled = false
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, index ->
            tab.text = when(index) {
                0 -> { "Theo dõi" }
                1 -> { "Cho bạn" }
                2 -> { "Bóng đá" }
                3 -> { "Công nghệ" }
                4 -> { "Đời sống" }
                else -> { throw Resources.NotFoundException("Position not found")}
            }
        }.attach()
    }
}