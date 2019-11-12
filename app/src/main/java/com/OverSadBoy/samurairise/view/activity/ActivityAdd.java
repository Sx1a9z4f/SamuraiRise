package com.OverSadBoy.samurairise.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.OverSadBoy.samurairise.R;
import com.OverSadBoy.samurairise.model.database.Item;


public class ActivityAdd extends Activity {

    private Spinner repeatState;
    private TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        repeatState = findViewById(R.id.repeat_state_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.repeat_state, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeatState.setAdapter(adapter);

        timePicker = findViewById(R.id.timePicker);

        ImageButton btnDone = findViewById(R.id.done_btn_add);
        btnDone.setOnClickListener(v -> {
            Item item = new Item(1, generateTime(), repeatState.getSelectedItem().toString(), "включен");
            Intent intent = new Intent();
            intent.putExtra("item", item);
            setResult(RESULT_OK, intent);
            finish();
        });

        ImageButton btnClose = findViewById(R.id.close_btn_add);
        btnClose.setOnClickListener(view -> finish());
    }

    private String generateTime() {
        return timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
    }

}




