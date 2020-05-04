package org.wit.nurse.models


var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class NurseMemStorage : NurseStore {

    val nurses = ArrayList<NurseModel>()

    override fun findAll(): List<NurseModel> {
        return nurses
    }

    override fun create(nurse: NurseModel) {
        nurse.id = getId()
        nurses.add(nurse)
        logAll()
    }

    override fun update(nurse: NurseModel) {
        var foundNurse: NurseModel? = nurses.find { p -> p.id == nurse.id }
        if (foundNurse != null) {
            foundNurse.name = nurse.name
            foundNurse.salary = nurse.salary
            foundNurse.address = nurse.address
            foundNurse.phoneNumber = nurse.phoneNumber
            foundNurse.ward = nurse.ward
            foundNurse.jobTitle = nurse.jobTitle
            logAll()
        }
    }

    override fun delete(nurse: NurseModel) {
        nurses.remove(nurse)
    }

    fun logAll(){
        nurses.forEach {("${it}")}
    }

}