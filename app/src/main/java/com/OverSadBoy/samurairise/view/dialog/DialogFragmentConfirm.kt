package com.OverSadBoy.samurairise.view.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.R
import org.junit.runner.RunWith
import java.util.*

class DialogFragmentConfirm : DialogFragment() {
    private var listener: DialogListener? = null
    fun setDialogListener(listener: DialogListener?) {
        this.listener = listener
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context)
                .setView(Objects.requireNonNull(activity).getLayoutInflater().inflate(R.layout.dialog, null))
                .setPositiveButton("Да") { dialog: DialogInterface?, which: Int -> listener.positiveClick() }
                .setNegativeButton("Нет") { dialog: DialogInterface?, which: Int -> listener.negativeClick() }
                .create()
    }
}