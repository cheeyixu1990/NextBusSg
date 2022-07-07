package com.androidndcapstone.android.nextbussg.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.androidndcapstone.android.nextbussg.R
import com.androidndcapstone.android.nextbussg.databinding.FragmentBusStopBinding

class BusStopFragment : Fragment() {

    private val viewModel: BusStopViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, BusStopViewModel.Factory(activity.application)).get(BusStopViewModel::class.java)
    }

    private lateinit var binding: FragmentBusStopBinding
    private lateinit var busStopListAdapter: BusStopListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?  {

        binding = FragmentBusStopBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        busStopListAdapter = BusStopListAdapter(
            BusStopClickListener {

            }
        )
        binding.busStopRecycler.adapter = busStopListAdapter
        viewModel.busStops.observe(viewLifecycleOwner, {
            it?.let {
                busStopListAdapter.submitList(it)
            }
        })

        return binding.root
    }

}