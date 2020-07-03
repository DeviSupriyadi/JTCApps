package com.devisupriyadi.jtcapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class OmaheFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_omahe, container, false)
    }

    companion object {
       fun NewInstance(): OmaheFragment{
           val fragment = OmaheFragment()
           val args = Bundle()
           fragment.arguments = args
           return fragment
       }
    }
}