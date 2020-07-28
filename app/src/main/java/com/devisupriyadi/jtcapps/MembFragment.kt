package com.devisupriyadi.jtcapps

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devisupriyadi.jtcapps.dialog.AddMemberDialog
import com.devisupriyadi.jtcapps.dialog.DeleteMemberDialog
import com.devisupriyadi.jtcapps.dialog.EditMemberDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_memb.view.*

class MembFragment : Fragment(), MembersAdapter.MemberItemClickListener {

    override fun memberItemClickListener(member: Member) {
        val memberID = member.memberID
        val membemNama = member.memberName
        val memberSabuk = member.memberSabuk
        val dialogEditMember: DialogFragment = EditMemberDialog.newInstance(
            memberID,membemNama,memberSabuk
        )
        dialogEditMember.show(fragmentManager!!,"edit_member")
    }

    override fun deleteItemClickListener(member: Member) {
        val dialogDeleteMember: DialogFragment = DeleteMemberDialog.newInstance(member.memberID)
        dialogDeleteMember.show(fragmentManager!!,"dialog_delete_member")
    }

    private lateinit var db: DatabaseReference
    private lateinit var cntx: Context
    private lateinit var membersAdapter: MembersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memb, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cntx = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseDatabase.getInstance().reference
        membersAdapter = MembersAdapter()

        db.child("members").addValueEventListener(
            object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {

                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val memberList = ArrayList<Member>()
                    for (memberSnapshot in snapshot.children){
                        val dataMember = memberSnapshot.getValue(Member::class.java)
                        memberList.add(dataMember!!)
                    }
                    membersAdapter.membersAdapter(memberList,this@MembFragment)
                    view.recyclerview_members.layoutManager = LinearLayoutManager(cntx)
                    view.recyclerview_members.adapter = membersAdapter
                }
            }
        )



        view.btn_add_membFragment.setOnClickListener {
            val dialogAddMember: DialogFragment = AddMemberDialog.newInstance()
            dialogAddMember.show(fragmentManager!!,"Add_Member_Dialog")
        }
    }
}