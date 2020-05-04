package org.wit.nurse.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NurseModel(var id: Long = 0,
                      var name: String = "",
                      var salary: Int = 0,
                      var address: String = "",
                      var phoneNumber: Int = 0,
                      var ward: String = "",
                      var jobTitle: String = "",
                      var imageName: String = ""
                        ) : Parcelable