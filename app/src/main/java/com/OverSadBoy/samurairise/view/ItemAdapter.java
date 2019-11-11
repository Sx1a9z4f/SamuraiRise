package com.OverSadBoy.samurairise.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.OverSadBoy.samurairise.R;
import com.OverSadBoy.samurairise.model.database.DataBase;
import com.OverSadBoy.samurairise.model.database.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> data;

    ItemAdapter(ArrayList<Item> alarms) {
        data = alarms;
        notifyDataSetChanged();
    }

    public void addData(Item item) {
        data.add(item);
        notifyDataSetChanged();
    }

    public int getAlarmsCount(){
        return data.size();
    }

    private void updateStatus(Item item, boolean status) {
        String str = "выключен";
        if (status)
            str = "включен";
        item.setStatus(str);
        notifyDataSetChanged();
    }

    public void deleteData(Item item) {
        data.remove(item.getId());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alarm, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
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

        void bind(Item item) {
            timeAlarm.setText(item.getTime());
            settingsAlarm.setText(String.format("" +
                    "%s | %s", item.getRepeat(), item.getStatus()));
            switchCompat.setChecked(item.getStatus().equals("включен"));
            switchCompat.setOnCheckedChangeListener((compoundButton, b) -> updateStatus(item, switchCompat.isChecked()));

        }
    }
}
