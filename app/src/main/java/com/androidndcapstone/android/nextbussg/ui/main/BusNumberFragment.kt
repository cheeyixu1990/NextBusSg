package com.androidndcapstone.android.nextbussg.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidndcapstone.android.nextbussg.R
import com.androidndcapstone.android.nextbussg.databinding.FragmentBusNumberBinding

class BusNumberFragment : Fragment() {

    private lateinit var binding: FragmentBusNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBusNumberBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
    }

    private fun setupData() {
        binding.label.text = getString(R.string.first_fragment)
    }
}
