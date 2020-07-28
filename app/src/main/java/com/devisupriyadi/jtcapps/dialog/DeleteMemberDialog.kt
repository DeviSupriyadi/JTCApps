package com.devisupriyadi.jtcapps.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.devisupriyadi.jtcapps.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.dialog_fragment_deletemamber.view.*


const val MEMBER_ID_DELETE = "MEMBER_ID"

class DeleteMemberDialog: DialogFragment() {

    private var memberID: String? = null
    private lateinit var cntx: Context
    private lateinit var db: DatabaseReference
    private lateinit var mView: View

    companion object{
        fun newInstance(memberID: String)=
            DeleteMemberDialog().apply {
                arguments = Bundle().apply {
                    putString(MEMBER_ID_DELETE,memberID)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            memberID = it.getString(MEMBER_ID_DELETE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.dialog_fragment_deletemamber,container,false)
        return mView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cntx = context
        db = FirebaseDatabase.getInstance().reference

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btn_delete_dialogDelete.setOnClickListener {
            db.child("members").child(memberID!!).removeValue()
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(cntx,"Berhasil delete memeber",Toast.LENGTH_SHORT).show()
                        this.dismiss()
                    }
                    else{
                        Toast.makeText(cntx,it.exception?.message,Toast.LENGTH_SHORT).show()
                    }
                }
        }
        view.btn_cancel_dialogCancel.setOnClickListener {
            this.dismiss()
        }
    }

}