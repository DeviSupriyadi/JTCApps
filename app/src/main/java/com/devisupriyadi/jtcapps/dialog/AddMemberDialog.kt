package com.devisupriyadi.jtcapps.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.devisupriyadi.jtcapps.Member
import com.devisupriyadi.jtcapps.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.dialog_fragment_addmember.*
import kotlinx.android.synthetic.main.dialog_fragment_addmember.view.*
import java.util.*

class AddMemberDialog: DialogFragment() {

    private lateinit var cntx: Context
    private lateinit var db: DatabaseReference
    private lateinit var mView: View

    companion object{
        fun newInstance() = AddMemberDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.dialog_fragment_addmember,container,false)
        return mView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cntx = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseDatabase.getInstance().reference

        view.btn_add_addMember.setOnClickListener {
            validasi()
        }


    }

    fun validasi(){
        val etMemberName = mView.et_memberName_addMember.text
        val etMemberSabuk = mView.et_memberSabuk_addMember.text
        if(etMemberName.isNullOrEmpty()){
            etLayout_memberName_addMember.isErrorEnabled = true
            etLayout_memberName_addMember.error = "Nama member tidak boleh kosong"
            return
        }
        etLayout_memberName_addMember.isErrorEnabled = false
        if(etMemberSabuk.isNullOrEmpty()){
            etLayout_memberSabuk_addMember.isErrorEnabled = true
            etLayout_memberSabuk_addMember.error = "Nama sabuk tidak boleh kosong"
            return
        }
        etLayout_memberSabuk_addMember.isErrorEnabled = false



        val memberID = UUID.randomUUID().toString()
        val members = Member(memberID,etMemberName.toString(),etMemberSabuk.toString())
        db.child("members").child(memberID).setValue(members)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    this.dismiss()
                }
                else{
                    Toast.makeText(cntx,it.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }

    }



}