package com.devisupriyadi.jtcapps

import android.view.View

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(
        view: View,
        member: Member
    )
}