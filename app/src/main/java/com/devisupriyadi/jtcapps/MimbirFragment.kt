package com.devisupriyadi.jtcapps

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_mimbir.*


class MimbirFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var viewModel: MembViewModel
    private val adapter = MembersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MembViewModel::class.java)
        return inflater.inflate(R.layout.fragment_mimbir, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter.listener = this
        recycler_view_member.adapter = adapter

        viewModel.fetchFilteredMembers(6)

        viewModel.members.observe(viewLifecycleOwner, Observer {
            adapter.setMember(it)
        })

        viewModel.member.observe(viewLifecycleOwner, Observer {
            adapter.addMember(it)
        })

        btn_add.setOnClickListener{
            AddMemberDialogFragment()
                .show(childFragmentManager, "")
        }
    }

    override fun onRecyclerViewItemClicked(view: View, member: Member) {
        when (view.id) {
            R.id.btn_edit -> {
                EditMemberDialogFragment(member).show(childFragmentManager, "")
            }
            R.id.btn_dlt -> {
                AlertDialog.Builder(requireContext()).also {
                    it.setTitle(getString(R.string.dlt_confirm))
                    it.setPositiveButton(getString(R.string.ya)) { dialog, which ->
                        viewModel.deleteMember(member)
                    }
                }.create().show()
            }
        }
    }

}