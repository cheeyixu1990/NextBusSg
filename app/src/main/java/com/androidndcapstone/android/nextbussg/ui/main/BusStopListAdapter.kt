package com.androidndcapstone.android.nextbussg.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidndcapstone.android.nextbussg.data.BusRepo
import com.androidndcapstone.android.nextbussg.data.BusStopBusService
import com.androidndcapstone.android.nextbussg.data.getDatabase
import com.androidndcapstone.android.nextbussg.databinding.ListItemBusStopBinding
import com.androidndcapstone.android.nextbussg.databinding.ListItemBusStopBusesBinding
import com.androidndcapstone.android.nextbussg.ui.model.BusService
import com.androidndcapstone.android.nextbussg.ui.model.BusStop
import kotlinx.coroutines.coroutineScope

class BusStopListAdapter(private val busStopClickListener: BusStopClickListener): ListAdapter<BusStop, BusStopListAdapter.BusStopViewHolder>(BusStopDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        return BusStopViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(busStopClickListener, item!!)
    }

    class BusStopViewHolder private constructor (private val binding: ListItemBusStopBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(clickListener: BusStopClickListener, item: BusStop) {
            binding.busStop = item
            val busStopListBusAdapter = BusStopListBusAdapter(
                BusStopListBusAdapter.BusStopBusClickListener {

                }
            )

            val horiLayoutManager = LinearLayoutManager(
                binding.root.context,
                RecyclerView.HORIZONTAL,
                false
            )

            binding.busStopBusRecycler.apply {
                adapter = busStopListBusAdapter
                layoutManager = horiLayoutManager
                setHasFixedSize(true)
                itemAnimator?.changeDuration = 0
            }

            val busRepo = BusRepo(getDatabase(binding.root.context))

            val busStopBusServices = busRepo.getBusServicesByBusStopCode(item.busStopCode)
            busStopBusServices.observeForever {
                busStopListBusAdapter.submitList(busStopBusServices.value)
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): BusStopViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBusStopBinding.inflate(layoutInflater, parent, false)

                return BusStopViewHolder(binding)
            }
        }

    }

    class BusStopClickListener(val busStopFunctionBlock: (BusStop) -> Unit) {
        fun onClick(busStop: BusStop) = busStopFunctionBlock(busStop)
    }


    class BusStopDiffCallback : DiffUtil.ItemCallback<BusStop>() {
        override fun areItemsTheSame(oldItem: BusStop, newItem: BusStop): Boolean {
            return oldItem.busStopCode == newItem.busStopCode
        }

        override fun areContentsTheSame(oldItem: BusStop, newItem: BusStop): Boolean {
            return oldItem == newItem
        }

    }
}

class BusStopListBusAdapter(private val busStopBusClickListener: BusStopBusClickListener): ListAdapter<BusStopBusService, BusStopListBusAdapter.BusStopBusViewHolder>(BusStopBusDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopBusViewHolder {
        return BusStopBusViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BusStopBusViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(busStopBusClickListener, item!!)
    }

    class BusStopBusViewHolder private constructor (private val binding: ListItemBusStopBusesBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(clickListener: BusStopBusClickListener, item: BusStopBusService) {
            binding.busService = item
//            binding.clickableOverlay.contentDescription = String.format("%s is approaching on %s, it is %s", item.codename, item.closeApproachDate, when(item.isPotentiallyHazardous) {
//                true -> "potentially hazardous."
//                else -> "safe."
//            })
//            binding.busStopClicked = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): BusStopBusViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBusStopBusesBinding.inflate(layoutInflater, parent, false)

                return BusStopBusViewHolder(binding)
            }
        }
    }

    class BusStopBusClickListener(val busStopBusFunctionBlock: (BusStopBusService) -> Unit) {
        fun onClick(busService: BusStopBusService) = busStopBusFunctionBlock(busService)
    }


    class BusStopBusDiffCallback : DiffUtil.ItemCallback<BusStopBusService>() {
        override fun areItemsTheSame(oldItem: BusStopBusService, newItem: BusStopBusService): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BusStopBusService, newItem: BusStopBusService): Boolean {
            return oldItem == newItem
        }

    }
}

