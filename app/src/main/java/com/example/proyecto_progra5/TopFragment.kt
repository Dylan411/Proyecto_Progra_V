package com.example.proyecto_progra5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto_progra5.databinding.FragmentSearchBinding
import com.example.proyecto_progra5.databinding.FragmentTopBinding


class TopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the Layout for this fragment
        return inflater.inflate(R.layout.fragment_top,container,false)
    }
}