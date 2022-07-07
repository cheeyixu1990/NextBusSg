package com.androidndcapstone.android.nextbussg.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidndcapstone.android.nextbussg.databinding.ListItemBusStopBinding
import com.androidndcapstone.android.nextbussg.ui.model.BusStop

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
//            binding.clickableOverlay.contentDescription = String.format("%s is approaching on %s, it is %s", item.codename, item.closeApproachDate, when(item.isPotentiallyHazardous) {
//                true -> "potentially hazardous."
//                else -> "safe."
//            })
//            binding.busStopClicked = clickListener
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