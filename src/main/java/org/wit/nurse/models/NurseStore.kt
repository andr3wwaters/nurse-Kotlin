package org.wit.nurse.models

interface NurseStore {
    fun findAll(): List<NurseModel>
    fun create(nurse: NurseModel)
    fun update(nurse: NurseModel)
    fun delete(nurse: NurseModel)
}