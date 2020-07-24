package com.devisupriyadi.jtcapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MembersAdapter : RecyclerView.Adapter<MembersAdapter.MembViewModel>(){

    private var member = mutableListOf<Member>()
    var listener: RecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MembViewModel(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_member, parent, false)
    )

    override fun getItemCount() = member.size

    @SupperssLint("SetTextI18n")
    override fun onBindViewHolder(holder: MembViewModel, position: Int) {
        holder.view.text_view_name.text = member[position].name
        holder.view.text_view_sabuk.text = member[position].sabuk
        holder.view.btn_edit.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, member[position])
        }
        holder.view.btn_dlt.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, member[position])
        }
    }

    fun setMember(member: List<Member>) {
        this.member = member as MutableList<Member>
        notifyDataSetChanged()
    }

    fun addMember(members: Member) {
        if (!member.contains(members)) {
            member.add(members)
        } else {
            val index = member.indexOf(members)
            if (members.isDeleted) {
                member.removeAt(index)
            } else {
                member[index] = members
            }
        }
        notifyDataSetChanged()
    }
}