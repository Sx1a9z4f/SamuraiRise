package com.OverSadBoy.samurairise.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.OverSadBoy.samurairise.R;
import com.OverSadBoy.samurairise.dagger.ContextModule;
import com.OverSadBoy.samurairise.dagger.DaggerPresenterComponent;
import com.OverSadBoy.samurairise.model.database.Item;
import com.OverSadBoy.samurairise.presenter.PresenterContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ViewContract {

    private ItemAdapter itemAdapter;
    private PresenterContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = DaggerPresenterComponent.builder().contextModule(new ContextModule(this)).build().getPresenter();
        presenter.attachView(this);
        itemAdapter = new ItemAdapter(presenter.getAlarms());

        RecyclerView recyclerView = findViewById(R.id.recyclerAlarm);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> presenter.addClick());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123 && resultCode == Activity.RESULT_OK)
            addAlarm(Objects.requireNonNull(data.getParcelableExtra("item")));
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void addAlarm(Item item) {
        item.setId(itemAdapter.getAlarmsCount());
        presenter.addAlarm(item);
        itemAdapter.addData(item);
    }

    @Override
    public void deleteAlarm(Item item) {
        presenter.deleteClick(item);
        itemAdapter.deleteData(item);
    }

    @Override
    public void updateStatus(Item item, boolean status) {

    }

    @Override
    public ArrayList<Item> loadData() {
        return presenter.getAlarms();
    }

    public void startActivityAdd() {
        startActivityForResult(new Intent(this, ActivityAdd.class), 123);
    }
}