package com.OverSadBoy.samurairise.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.OverSadBoy.samurairise.R;
import com.OverSadBoy.samurairise.dagger.ContextModule;
import com.OverSadBoy.samurairise.dagger.DaggerPresenterComponent;
import com.OverSadBoy.samurairise.model.database.Item;
import com.OverSadBoy.samurairise.presenter.PresenterContract;
import com.OverSadBoy.samurairise.view.ViewContract;
import com.OverSadBoy.samurairise.view.dialog.DialogFragmentConfirm;
import com.OverSadBoy.samurairise.view.dialog.DialogListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ViewContract {

    private ItemAdapter itemAdapter;
    private PresenterContract presenter;
    private ActionMode actionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = DaggerPresenterComponent.builder().contextModule(new ContextModule(this)).build().getPresenter();
        presenter.attachView(this);
        itemAdapter = new ItemAdapter(presenter.getAlarms());
        itemAdapter.setListener(new AdapterListener());

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
        presenter.addAlarm(item);
        itemAdapter.addData(item);
    }

    @Override
    public void deleteAlarm(Item item) {
        presenter.deleteClick(item.getId());
        itemAdapter.deleteData(item.getId());
    }

    @Override
    public ArrayList<Item> loadData() {
        return presenter.getAlarms();
    }

    public void startActivityAdd() {
        startActivityForResult(new Intent(this, ActivityAdd.class), 123);
    }

    private void switchStatus(Item item, boolean status) {
        itemAdapter.switchStatus(item, status);
    }


    private boolean isInActionMode() {
        return actionMode != null;
    }

    private void toggleSelection(Item item, int position) {
        itemAdapter.toggleSelection(item, position);
    }


    private void removeSelectedItems() {
        for (int i = itemAdapter.getSelectedItems().size() - 1; i >= 0; i--) {
            itemAdapter.deleteData(itemAdapter.getSelectedItemsPosition().get(i));
            presenter.deleteClick(itemAdapter.getSelectedItems().get(i).getId());
        }
        actionMode.finish();
    }


    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = new MenuInflater(getApplicationContext());
            inflater.inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.remove)
                showDialog();
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            itemAdapter.clearSelections();
            actionMode = null;
        }
    };

    private void showDialog() {
        DialogFragmentConfirm dialog = new DialogFragmentConfirm();
        dialog.show(getSupportFragmentManager(), "ConfirmationDialog");
        dialog.setDialogListener(new DialogListener() {
            @Override
            public void positiveClick() {
                removeSelectedItems();
            }

            @Override
            public void negativeClick() {
                actionMode.finish();
            }
        });
    }

    private class AdapterListener implements ItemsAdapterListener {

        @Override
        public void onSwitchClick(Item item, boolean status, int position) {
            switchStatus(item, status);
        }

        @Override
        public void onItemClick(Item item, int position) {
            if (isInActionMode()) {
                toggleSelection(item, position);
            }
        }

        @Override
        public void onItemLongClick(Item item, int position) {
            if (isInActionMode()) {
                return;
            }
            actionMode = startSupportActionMode(actionModeCallback);
            toggleSelection(item, position);
        }
    }
}