package com.devisupriyadi.jtcapps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kontak.*

class KontakFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kontak, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ig1.setOnClickListener(){
            var i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/jtc_team/"))
            startActivity(i)
        }
        btn_maps.setOnClickListener(){
            var i = Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/xBzJEVzaGWB2vbbw5"))
            startActivity(i)
        }
    }

    companion object {
    }
}