package com.example.retrofitcallapidemo.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.retrofitcallapidemo.MainViewModel
import com.example.retrofitcallapidemo.R
import com.example.retrofitcallapidemo.databinding.FragmentDetailBinding
import com.example.retrofitcallapidemo.model.DocumentModel
import com.example.retrofitcallapidemo.ui.home.ImageAdapter

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var itemChoose: DocumentModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindView()
        onClickListener()
    }

    private fun initData() {
        itemChoose = mainViewModel.itemChoose
    }

    private fun bindView() {
        binding.apply {
            tvTitle.text = itemChoose.title
            Glide.with(requireContext()).load(itemChoose.images?.get(0)?.href)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_background).into(img)
            tvPublisher.text = itemChoose.publisher.name
            tvDescription.text = itemChoose.description
            rcv.apply {
                adapter = itemChoose.images?.let { ImageAdapter(requireContext(), it) }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun onClickListener() {
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}