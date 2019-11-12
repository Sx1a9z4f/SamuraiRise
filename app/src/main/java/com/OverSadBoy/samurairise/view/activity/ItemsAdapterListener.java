package com.OverSadBoy.samurairise.view.activity;

import com.OverSadBoy.samurairise.model.database.Item;

public interface ItemsAdapterListener {
    void onSwitchClick(Item item, boolean status,int position);

    void onItemClick(Item item,int position);

    void onItemLongClick(Item item,int position);
}
