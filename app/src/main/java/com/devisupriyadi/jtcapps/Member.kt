package com.devisupriyadi.jtcapps

import com.google.firebase.database.Exclude

private val sbk = listOf("Geup 10, Geup 9, Geup 8, Geup 7, Geup 6, Geup 5, Geup 4, Geup 3, Geup 2, Geup 1, DAN 1, DAN 2, DAN 3, DAN 4")

data class Member(
    @get:Exclude
    var id: String? = null,
    var name: String? = null,
    var sabuk: String? = sbk.random(),
    @get:Exclude
    var isDeleted: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return if (other is Member) {
            other.id == id
        } else false
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?:0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}