package com.OverSadBoy.samurairise.view.activity;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.OverSadBoy.samurairise.R;
import com.OverSadBoy.samurairise.model.database.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> data;
    private ItemsAdapterListener listener = null;

    ItemAdapter(ArrayList<Item> alarms) {
        data = alarms;
        notifyDataSetChanged();
    }

    public void setListener(ItemsAdapterListener listener) {
        this.listener = listener;
    }

    public void addData(Item item) {
        data.add(item);
        notifyDataSetChanged();
    }

    public int getAlarmsCount() {
        return data.size();
    }

    private void updateStatus(Item item, boolean status) {
        String str = "выключен";
        if (status)
            str = "включен";
        item.setStatus(str);
        notifyDataSetChanged();
    }

    public void deleteData(int id) {
        data.remove(id);
        notifyDataSetChanged();
    }

    public void switchStatus(Item item, boolean status) {
        if (status)
            data.get(item.getId()).setStatus("включен");
        else
            data.get(item.getId()).setStatus("выключен");
        notifyDataSetChanged();
    }

    private SparseBooleanArray selections = new SparseBooleanArray();
    private ArrayList<Item> selectionItem = new ArrayList<>();

    public void toggleSelection(Item item,int position) {
        if (selections.get(position, false)) {
            selections.delete(position);
            selectionItem.remove(item);
        } else {
            selections.put(position, true);
            selectionItem.add(item);
        }
        notifyItemChanged(position);
    }

    void clearSelections() {
        selections.clear();
        selectionItem.clear();
        notifyDataSetChanged();
    }

    public ArrayList<Item> getSelectedItems() {
        return selectionItem;
    }

    ArrayList<Integer> getSelectedItemsPosition() {
        ArrayList<Integer> items = new ArrayList<>(selections.size());
        for (int i = 0; i < selections.size(); i++) {
            items.add(selections.keyAt(i));
        }
        return items;
    }


    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alarm, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position),position, listener, data.get(position).getStatus().equals("включен"),selections.get(position,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView timeAlarm;
        private final TextView settingsAlarm;
        private final SwitchCompat switchCompat;


        ViewHolder(View itemView) {
            super(itemView);
            timeAlarm = itemView.findViewById(R.id.timeAlarm);
            settingsAlarm = itemView.findViewById(R.id.settingsAlarm);
            switchCompat = itemView.findViewById(R.id.statusAlarm);
        }

        void bind(Item item,int position, ItemsAdapterListener listener, boolean status, boolean selected) {
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(item,position);
                }
            });
            itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    listener.onItemLongClick(item,position);
                }
                return true;
            });
            itemView.setActivated(selected);
            timeAlarm.setText(item.getTime());
            settingsAlarm.setText(String.format("" +
                    "%s | %s", item.getRepeat(), item.getStatus()));
            switchCompat.setChecked(status);
            switchCompat.setOnCheckedChangeListener((compoundButton, b) -> {
                if (listener != null)
                    listener.onSwitchClick(item, b,position);
            });
        }
    }
}
