package com.devisupriyadi.jtcapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_member.view.*

class MembersAdapter : RecyclerView.Adapter<MembersAdapter.MembViewHolder>(){

    private lateinit var memberList: List<Member>
    private lateinit var clickListener: MemberItemClickListener

    interface MemberItemClickListener{
        fun memberItemClickListener(member: Member)
        fun deleteItemClickListener(member: Member)
    }

    class MembViewHolder(view:View):RecyclerView.ViewHolder(view){
        val textviewName = view.text_view_name_rvMember
        val textviewSabuk = view.text_view_sabuk_rvMember
        val btnedit = view.btn_edit_rvMember
        val btndelete = view.btn_dlt_rvMember

        fun b(member: Member,clickListener:MemberItemClickListener){
            textviewName.text = member.memberName
            textviewSabuk.text = member.memberSabuk

            btnedit.setOnClickListener {
                clickListener.memberItemClickListener(member)
            }
            btndelete.setOnClickListener {
                clickListener.deleteItemClickListener(member)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembViewHolder {
        return MembViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_member,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MembViewHolder, position: Int) {
        holder.b(memberList.get(position),clickListener)
    }

    fun membersAdapter(memberList: List<Member>,clickListener: MemberItemClickListener){
        this.memberList = memberList
        this.clickListener = clickListener
        notifyDataSetChanged()
    }
}