package org.wit.nurse.activities

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_nurse.*
import kotlinx.android.synthetic.main.card_nurse.*
import kotlinx.android.synthetic.main.activity_nurse.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.nurse.models.NurseModel
import org.wit.nurse.R
import org.wit.nurse.helpers.readImage
import org.wit.nurse.helpers.readImageFromPath
import org.wit.nurse.helpers.showImagePicker
import org.wit.nurse.main.MainApp

class NurseActivity : AppCompatActivity(), AnkoLogger {

    var nurse = NurseModel()
    lateinit var app : MainApp
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    var edit = false

    private lateinit var toolbarAdd: Toolbar
    private lateinit var nurseNameEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText
    private lateinit var salaryEditText: TextInputEditText
    private lateinit var chooseImageButton: Button
    private lateinit var nurseImageView: ImageView
    private lateinit var nurseSalaryButton: Button
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nurse)
        app = application as MainApp

        nurseNameEditText = findViewById(R.id.nurseNameEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        salaryEditText = findViewById(R.id.salaryEditText)
        chooseImageButton = findViewById(R.id.chooseImageButton)
        nurseImageView = findViewById(R.id.nurseImageView)
        nurseSalaryButton = findViewById(R.id.nurseSalaryButton)
        addButton = findViewById(R.id.addButton)

        toolbarAdd = findViewById(R.id.toolbarAdd)
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("nurse_edit")) {
            edit = true
            nurse = intent.extras.getParcelable<NurseModel>("nurse_edit")
            nurseNameEditText.setText(nurse.name)
            salaryEditText.setText(nurse.salary.toString())
            if(nurse.imageName.isNotEmpty()) {
                nurseImageView.setImageBitmap(readImageFromPath(this, nurse.imageName))
            }
            else {
                chooseImageButton.setText(R.string.change_nurse_image)
            }
            addButton.setText(R.string.save_nurse)
        }


        addButton.setOnClickListener() {
            nurse.name = nurseName.text.toString()
            nurse.salary = salaryEditText.text.toString().toInt()

            if (nurse.name.isEmpty()) {
                toast(R.string.enter_nurse_name)
            } else {
                if (edit) {
                    app.nurses.update(nurse.copy())
                } else {
                    app.nurses.create(nurse.copy())
                }
            }
            info("add Button Pressed: $nurseName")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        chooseImageButton.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nurse, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.nurses.delete(nurse)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
//                    nurse.image = data.getData().toString()
//                    placemarkImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImageButton.setText(R.string.change_nurse_image)
                }
            }
        }
    }
}