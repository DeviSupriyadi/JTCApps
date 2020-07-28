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
import kotlinx.android.synthetic.main.dialog_fragment_editmember.*
import kotlinx.android.synthetic.main.dialog_fragment_editmember.view.*
import java.util.*
import kotlin.collections.HashMap

const val MEMBER_ID = "MEMBER_ID"
const val MEMBER_NAMA = "MEMBER_NAMA"
const val MEMBER_SABUK = "MEMBER_SABUK"

class EditMemberDialog: DialogFragment() {

    private lateinit var mView: View
    private var memberID: String? = null
    private var memberNama: String? = null
    private var memberSabuk: String? = null
    private lateinit var db: DatabaseReference
    private lateinit var cntx: Context

    companion object{
        fun newInstance(memberID: String, memberNama: String, memberSabuk: String) =
            EditMemberDialog().apply {
                arguments = Bundle().apply {
                    putString(MEMBER_ID,memberID)
                    putString(MEMBER_NAMA,memberNama)
                    putString(MEMBER_SABUK,memberSabuk)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            memberID = it.getString(MEMBER_ID)
            memberNama = it.getString(MEMBER_NAMA)
            memberSabuk = it.getString(MEMBER_SABUK)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.dialog_fragment_editmember,container,false)
        return mView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cntx = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseDatabase.getInstance().reference

        view.edittext_name_editDialog.setText(memberNama)
        view.edittext_sabuk_editDialog.setText(memberSabuk)

        view.btn_updateMember_editDialog.setOnClickListener {
            validasi()
        }
    }

    fun validasi(){
        val etMemberName = mView.edittext_name_editDialog.text
        val etMemberSabuk = mView.edittext_sabuk_editDialog.text
        if(etMemberName.isNullOrEmpty()){
            inputlayout_name_editDialog.isErrorEnabled = true
            inputlayout_name_editDialog.error = "Nama member tidak boleh kosong"
            return
        }
        inputlayout_name_editDialog.isErrorEnabled = false
        if(etMemberSabuk.isNullOrEmpty()){
            inputlayout_sabuk_editDialog.isErrorEnabled = true
            inputlayout_sabuk_editDialog.error = "Nama sabuk tidak boleh kosong"
            return
        }
        inputlayout_sabuk_editDialog.isErrorEnabled = false



        val memberMap = HashMap<String,Any>()
        memberMap.put("memberName",etMemberName.toString())
        memberMap.put("memberSabuk",etMemberSabuk.toString())
        db.child("members").child(memberID!!)
            .updateChildren(memberMap)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(cntx,"Update member berhasil",Toast.LENGTH_SHORT).show()
                    this.dismiss()
                }
                else{
                    Toast.makeText(cntx,it.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }

    }
}