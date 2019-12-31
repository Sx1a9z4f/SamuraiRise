package com.overSadBoy.samurairise.view.activity.alarms

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.runner.AndroidJUnit4
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.overSadBoy.samurairise.R
import com.overSadBoy.samurairise.dagger.DaggerPresenterComponent
import com.overSadBoy.samurairise.model.database.Alarm
import com.overSadBoy.samurairise.presenter.PresenterContract
import com.overSadBoy.samurairise.view.ViewContract
import com.overSadBoy.samurairise.view.activity.add.ActivityAdd
import com.overSadBoy.samurairise.view.adapter.ItemAdapter
import com.overSadBoy.samurairise.view.adapter.ItemsAdapterListener
import com.overSadBoy.samurairise.view.dialog.DialogFragmentConfirm
import com.overSadBoy.samurairise.view.dialog.DialogListener
import org.junit.runner.RunWith
import java.util.*

class MainActivity : AppCompatActivity(), ViewContract {
    private var itemAdapter: ItemAdapter? = null
    private var presenter: PresenterContract? = null
    private var actionMode: ActionMode? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = DaggerPresenterComponent.builder().contextModule(ContextModule(this)).build().getPresenter()
        presenter.attachView(this)
        itemAdapter = ItemAdapter(presenter.getAlarms())
        itemAdapter.setListener(AdapterListener())
        val recyclerView = findViewById<RecyclerView?>(R.id.recyclerAlarm)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(itemAdapter)
        val fab = findViewById<FloatingActionButton?>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener { v: View? -> presenter.addClick() })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) addAlarm(Objects.requireNonNull(data.getParcelableExtra("item")))
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun addAlarm(alarm: Alarm?) {
        presenter.addAlarm(alarm)
        itemAdapter.addData(alarm)
    }

    override fun deleteAlarm(alarm: Alarm?) {
        presenter.deleteClick(alarm.getId())
        itemAdapter.deleteData(alarm.getId())
    }

    override fun loadData(): ArrayList<Alarm?>? {
        return presenter.getAlarms()
    }

    override fun startActivityAdd() {
        startActivityForResult(Intent(this, ActivityAdd::class.java), 123)
    }

    private fun switchStatus(alarm: Alarm?, status: Boolean) {
        itemAdapter.switchStatus(alarm, status)
    }

    private fun isInActionMode(): Boolean {
        return actionMode != null
    }

    private fun toggleSelection(alarm: Alarm?, position: Int) {
        itemAdapter.toggleSelection(alarm, position)
    }

    private fun removeSelectedItems() {
        for (i in itemAdapter.getSelectedItems().indices.reversed()) {
            itemAdapter.deleteData(itemAdapter.getSelectedItemsPosition()[i])
            presenter.deleteClick(itemAdapter.getSelectedItems()[i].id)
        }
        actionMode.finish()
    }

    private val actionModeCallback: ActionMode.Callback? = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val inflater = MenuInflater(applicationContext)
            inflater.inflate(R.menu.menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            if (item.getItemId() == R.id.remove) showDialog()
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            itemAdapter.clearSelections()
            actionMode = null
        }
    }

    private fun showDialog() {
        val dialog = DialogFragmentConfirm()
        dialog.show(supportFragmentManager, "ConfirmationDialog")
        dialog.setDialogListener(object : DialogListener {
            override fun positiveClick() {
                removeSelectedItems()
            }

            override fun negativeClick() {
                actionMode.finish()
            }
        })
    }

    private inner class AdapterListener : ItemsAdapterListener {
        override fun onSwitchClick(alarm: Alarm?, status: Boolean, position: Int) {
            switchStatus(alarm, status)
        }

        override fun onItemClick(alarm: Alarm?, position: Int) {
            if (isInActionMode()) {
                toggleSelection(alarm, position)
            }
        }

        override fun onItemLongClick(alarm: Alarm?, position: Int) {
            if (isInActionMode()) {
                return
            }
            actionMode = startSupportActionMode(actionModeCallback)
            toggleSelection(alarm, position)
        }
    }
}