package com.example.proyecto_progra5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.proyecto_progra5.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the Layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtTitleHome.isVisible = false
        txtSiteHome.isVisible = false
        txtCityHome.isVisible = false
        imageViewHome.isVisible = false
    }

    override fun onStart() {
        super.onStart()
        val db = FirebaseFirestore.getInstance()

        db.collection("sites").document("soda").get().addOnSuccessListener {
            txtTitleHome.text = it.get("name") as String?
            txtSiteHome.text = it.get("type") as String?
            txtCityHome.text = it.get("site") as String?
        }

        txtTitleHome.isVisible = true
        txtSiteHome.isVisible = true
        txtCityHome.isVisible = true
        imageViewHome.isVisible = true
    }
}