package com.example.proyecto_progra5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto_progra5.databinding.FragmentPerfilBinding
import kotlinx.android.synthetic.main.fragment_perfil.*


class PerilFragment : Fragment() {
    private lateinit var binding: FragmentPerfilBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPerfilBinding.inflate(layoutInflater)
        return binding.root
    }

}

