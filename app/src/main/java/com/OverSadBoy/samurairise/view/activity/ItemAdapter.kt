package com.OverSadBoy.samurairise.view.activity

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.R
import com.OverSadBoy.samurairise.model.database.Item
import org.junit.runner.RunWith
import java.util.*

class ItemAdapter internal constructor(alarms: ArrayList<Item?>?) : RecyclerView.Adapter<ItemAdapter.ViewHolder?>() {
    private val data: MutableList<Item?>?
    private var listener: ItemsAdapterListener? = null
    fun setListener(listener: ItemsAdapterListener?) {
        this.listener = listener
    }

    fun addData(item: Item?) {
        data.add(item)
        notifyDataSetChanged()
    }

    fun getAlarmsCount(): Int {
        return data.size
    }

    private fun updateStatus(item: Item?, status: Boolean) {
        var str = "выключен"
        if (status) str = "включен"
        item.setStatus(str)
        notifyDataSetChanged()
    }

    fun deleteData(id: Int) {
        data.removeAt(id)
        notifyDataSetChanged()
    }

    fun switchStatus(item: Item?, status: Boolean) {
        if (status) data.get(item.getId()).setStatus("включен") else data.get(item.getId()).setStatus("выключен")
        notifyDataSetChanged()
    }

    private val selections: SparseBooleanArray? = SparseBooleanArray()
    private val selectionItem: ArrayList<Item?>? = ArrayList()
    fun toggleSelection(item: Item?, position: Int) {
        if (selections.get(position, false)) {
            selections.delete(position)
            selectionItem.remove(item)
        } else {
            selections.put(position, true)
            selectionItem.add(item)
        }
        notifyItemChanged(position)
    }

    fun clearSelections() {
        selections.clear()
        selectionItem.clear()
        notifyDataSetChanged()
    }

    fun getSelectedItems(): ArrayList<Item?>? {
        return selectionItem
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
        fun bind(item: Item?, position: Int, listener: ItemsAdapterListener?, status: Boolean, selected: Boolean) {
            itemView.setOnClickListener { v: View? ->
                listener?.onItemClick(item, position)
            }
            itemView.setOnLongClickListener { v: View? ->
                listener?.onItemLongClick(item, position)
                true
            }
            itemView.isActivated = selected
            timeAlarm.setText(item.getTime())
            settingsAlarm.setText(String.format("" +
                    "%s | %s", item.getRepeat(), item.getStatus()))
            switchCompat.setChecked(status)
            switchCompat.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton: CompoundButton?, b: Boolean -> listener?.onSwitchClick(item, b, position) })
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