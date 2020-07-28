package com.devisupriyadi.jtcapps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import java.lang.Exception

class MembViewModel : ViewModel() {

    private val dbMember = FirebaseDatabase.getInstance().getReference(NODE_MEMBERS)


    private val _members = MutableLiveData<List<Member>>()
    val members: LiveData<List<Member>>
        get() = _members

    private val _member = MutableLiveData<Member>()
    val member: LiveData<Member>
        get() = _member

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    fun addMember(member: Member) {
        member.id = dbMember.push().key
        dbMember.child(member.id!!).setValue(member)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

    private val childEventListener = object : ChildEventListener {
        override fun onCancelled(error: DatabaseError) {}

        override fun onChildMoved(snapshot: DataSnapshot, p1: String?) {}

        override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
            val member = snapshot.getValue(Member::class.java)
            member?.id = snapshot.key
            _member.value = member
        }

        override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
            val member = snapshot.getValue(Member::class.java)
            member?.id = snapshot.key
            _member.value = member
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            val member = snapshot.getValue(Member::class.java)
            member?.id = snapshot.key
            member?.isDeleted = true
            _member.value = member
        }
    }
    fun getRealtimeUpdates() {
        dbMember.addChildEventListener(childEventListener)
    }

    fun fetchFilteredMembers(index: Int) {
        val dbMember =
            when (index) {
                1 ->
                    FirebaseDatabase.getInstance().getReference(NODE_MEMBERS)
                2 ->
                    FirebaseDatabase.getInstance().getReference(NODE_MEMBERS)
                        .child("-M-3fFw3GbovXWguSjp8")
                3 ->
                    FirebaseDatabase.getInstance().getReference(NODE_MEMBERS)
                        .orderByChild("sabuk")
                        .equalTo("Sabuk")
                4 ->
                    FirebaseDatabase.getInstance().getReference(NODE_MEMBERS)
                        .limitToFirst(2)
                5 ->
                    FirebaseDatabase.getInstance().getReference(NODE_MEMBERS)
                        .orderByChild("name")
                        .startAt("A")
                        .endAt("A\uf8ff")
                6 ->
                    FirebaseDatabase.getInstance().getReference(NODE_MEMBERS)
                else -> FirebaseDatabase.getInstance().getReference(NODE_MEMBERS)
            }

        dbMember.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val members = mutableListOf<Member>()
                    for (memberSnapshot in snapshot.children) {
                        val member = memberSnapshot.getValue(Member::class.java)
                        member?.id = memberSnapshot.key
                        member?.let { members.add(it) }
                    }
                    _members.value = members
                }
            }
        })
    }


    fun fetchMembers() {
        dbMember.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val members = mutableListOf<Member>()
                    for (memberSnapshot in snapshot.children) {
                        val member = memberSnapshot.getValue(Member::class.java)
                        member?.id = memberSnapshot.key
                        member?.let { members.add(it) }
                    }
                    _members.value = members
                }
            }
        })
    }

    fun updateMember(member: Member) {
        dbMember.child(member.id!!).setValue(member)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

    fun deleteMember(member: Member) {
        dbMember.child(member.id!!).setValue(null)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        dbMember.removeEventListener(childEventListener)
    }
}