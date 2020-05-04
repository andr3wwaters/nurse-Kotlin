package org.wit.nurse.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.nurse.R
import org.wit.nurse.main.MainApp
import org.wit.nurse.models.NurseModel

class NurseListActivity : AppCompatActivity(), NurseListener {

    lateinit var app: MainApp

    private lateinit var toolbarMain: Toolbar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nurse_list)
        app = application as MainApp

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val bootStrapData = listOf(
            NurseModel(name = "Nurse 1", salary = 40000),
            NurseModel(name = "Nurse 2", salary = 45000),
            NurseModel(name = "Nurse 2", salary = 50000))
        recyclerView.adapter = NurseAdapter(bootStrapData, this)
        loadNurses()

        //enable action bar and set title
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> {
                startActivityForResult<NurseActivity>(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNurseClick(nurse: NurseModel) {
        startActivityForResult(intentFor<NurseActivity>().putExtra("nurse_edit", nurse), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadNurses()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadNurses() {
        showNurses(app.nurses.findAll())
        recyclerView.adapter?.notifyDataSetChanged()
    }

    fun showNurses (nurses: List<NurseModel>) {
        recyclerView.adapter?.notifyDataSetChanged()
    }

}