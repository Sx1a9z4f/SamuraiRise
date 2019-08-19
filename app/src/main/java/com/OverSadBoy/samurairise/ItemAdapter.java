package com.OverSadBoy.samurairise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> data = new ArrayList<>();

     ItemAdapter() {
        createData();
    }

    private void createData() {
        data.add(new Item("15:00", "пн", "включен"));
        data.add(new Item("11:22", "ср пт", "включен"));
        data.add(new Item("8:30", "чт", "выключен"));

    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alarm, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView timeAlarm;
        private final TextView settingsAlarm;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeAlarm = itemView.findViewById(R.id.timeAlarm);
            settingsAlarm = itemView.findViewById(R.id.settingsAlarm);
        }

        void bind(Item item) {
            timeAlarm.setText(item.getTime());
            settingsAlarm.setText(item.getRepeat() + " | " + item.getStatus());
        }
    }
}
