package org.wit.nurse.main

import android.app.Application
import org.wit.nurse.models.NurseStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.nurse.models.NurseJSON

class MainApp : Application(), AnkoLogger  {

    lateinit var nurses : NurseStore

    override fun onCreate() {
        super.onCreate()
        nurses = NurseJSON(applicationContext)
        info("Nurse Started")
        }
    }



