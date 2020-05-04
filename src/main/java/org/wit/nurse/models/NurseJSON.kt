package org.wit.nurse.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.nurse.helpers.*
import java.util.*

val JSON_FILE = "nurses.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<NurseModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class NurseJSON : NurseStore, AnkoLogger {

    val context: Context
    var nurses = mutableListOf<NurseModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<NurseModel> {
        return nurses
    }

    override fun create(nurse: NurseModel) {
        nurse.id = generateRandomId()
        nurses.add(nurse)
        serialize()
    }


    override fun update(nurse: NurseModel) {
        val placemarksList = findAll() as ArrayList<NurseModel>
        var foundNurse: NurseModel? = nurses.find { p -> p.id == nurse.id }
        if (foundNurse != null) {
            foundNurse.name = nurse.name
            foundNurse.salary = nurse.salary
            foundNurse.address = nurse.address
            foundNurse.phoneNumber = nurse.phoneNumber
            foundNurse.ward = nurse.ward
            foundNurse.jobTitle = nurse.jobTitle
        }
        serialize()
    }

    override fun delete(nurse: NurseModel) {
        nurses.remove(nurse)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(nurses, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        nurses = Gson().fromJson(jsonString, listType)
    }
}

