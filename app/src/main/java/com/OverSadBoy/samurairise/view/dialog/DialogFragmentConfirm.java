package com.OverSadBoy.samurairise.view.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.OverSadBoy.samurairise.R;

import java.util.Objects;


public class DialogFragmentConfirm extends DialogFragment {

    private DialogListener listener = null;

    public void setDialogListener(DialogListener listener) {
        this.listener = listener;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setView(Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.dialog, null))
                .setPositiveButton("Да", (dialog, which) -> listener.positiveClick())
                .setNegativeButton("Нет", (dialog, which) -> listener.negativeClick())
                .create();

    }
}
