package com.androidndcapstone.android.nextbussg.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.androidndcapstone.android.nextbussg.R
import com.androidndcapstone.android.nextbussg.ui.model.BusStop

@BindingAdapter("busStopText")
fun bindTextViewToBusStopText(textView: TextView, busStop: BusStop) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.bus_stop_code_descp_formatted), busStop.busStopCode, busStop.busStopDescription)
}