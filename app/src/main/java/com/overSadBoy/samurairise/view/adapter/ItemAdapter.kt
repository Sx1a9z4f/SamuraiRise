package com.overSadBoy.samurairise.view.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.runner.AndroidJUnit4
import com.overSadBoy.samurairise.R
import com.overSadBoy.samurairise.model.Alarm
import org.junit.runner.RunWith
import java.util.*

class ItemAdapter internal constructor(alarms: ArrayList<Alarm?>?) : RecyclerView.Adapter<ItemAdapter.ViewHolder?>() {
    private val data: MutableList<Alarm?>?
    private var listener: ItemsAdapterListener? = null
    fun setListener(listener: ItemsAdapterListener?) {
        this.listener = listener
    }

    fun addData(alarm: Alarm?) {
        data.add(alarm)
        notifyDataSetChanged()
    }

    fun getAlarmsCount(): Int {
        return data.size
    }

    private fun updateStatus(alarm: Alarm?, status: Boolean) {
        var str = "выключен"
        if (status) str = "включен"
        alarm.setStatus(str)
        notifyDataSetChanged()
    }

    fun deleteData(id: Int) {
        data.removeAt(id)
        notifyDataSetChanged()
    }

    fun switchStatus(alarm: Alarm?, status: Boolean) {
        if (status) data.get(alarm.getId()).setStatus("включен") else data.get(alarm.getId()).setStatus("выключен")
        notifyDataSetChanged()
    }

    private val selections: SparseBooleanArray? = SparseBooleanArray()
    private val selectionAlarm: ArrayList<Alarm?>? = ArrayList()
    fun toggleSelection(alarm: Alarm?, position: Int) {
        if (selections.get(position, false)) {
            selections.delete(position)
            selectionAlarm.remove(alarm)
        } else {
            selections.put(position, true)
            selectionAlarm.add(alarm)
        }
        notifyItemChanged(position)
    }

    fun clearSelections() {
        selections.clear()
        selectionAlarm.clear()
        notifyDataSetChanged()
    }

    fun getSelectedItems(): ArrayList<Alarm?>? {
        return selectionAlarm
    }

    fun getSelectedItemsPosition(): ArrayList<Int?>? {
        val items = ArrayList<Int?>(selections.size())
        for (i in 0 until selections.size()) {
            items.add(selections.keyAt(i))
        }
        return items
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alarm, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder.bind(data.get(position), position, listener, data.get(position).getStatus() == "включен", selections.get(position, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        private val timeAlarm: TextView?
        private val settingsAlarm: TextView?
        private val switchCompat: SwitchCompat?
        fun bind(alarm: Alarm?, position: Int, listener: ItemsAdapterListener?, status: Boolean, selected: Boolean) {
            itemView.setOnClickListener { v: View? ->
                listener?.onItemClick(alarm, position)
            }
            itemView.setOnLongClickListener { v: View? ->
                listener?.onItemLongClick(alarm, position)
                true
            }
            itemView.isActivated = selected
            timeAlarm.setText(alarm.getTime())
            settingsAlarm.setText(String.format("" +
                    "%s | %s", alarm.getRepeat(), alarm.getStatus()))
            switchCompat.setChecked(status)
            switchCompat.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton: CompoundButton?, b: Boolean -> listener?.onSwitchClick(alarm, b, position) })
        }

        init {
            timeAlarm = itemView.findViewById<TextView?>(R.id.timeAlarm)
            settingsAlarm = itemView.findViewById<TextView?>(R.id.settingsAlarm)
            switchCompat = itemView.findViewById(R.id.statusAlarm)
        }
    }

    init {
        data = alarms
        notifyDataSetChanged()
    }
}