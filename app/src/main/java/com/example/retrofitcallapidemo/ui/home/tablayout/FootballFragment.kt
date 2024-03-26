package com.example.retrofitcallapidemo.ui.home.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitcallapidemo.MainViewModel
import com.example.retrofitcallapidemo.R
import com.example.retrofitcallapidemo.databinding.FragmentFootballBinding
import com.example.retrofitcallapidemo.model.DocumentModel
import com.example.retrofitcallapidemo.ui.home.DocumentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballFragment : Fragment() {
    private lateinit var binding: FragmentFootballBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFootballBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        mainViewModel.listFootball.observe(viewLifecycleOwner) {
            binding.rcv.apply {
                adapter = DocumentAdapter(it, onItemClick)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }
        }
    }

    private val onItemClick: (document: DocumentModel) -> Unit = {
        mainViewModel.itemChoose = it
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }
}