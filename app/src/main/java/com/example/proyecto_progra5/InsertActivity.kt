package com.example.proyecto_progra5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_insert_sites.*
import kotlinx.android.synthetic.main.fragment_perfil.*

class InsertActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_insert_sites)

        btnInsertSite.setOnClickListener {
            db.collection("sites").document(txtInsertName.text.toString()).set(
                hashMapOf("name" to txtInsertName.text.toString(),
                "type" to txtInsertType.text.toString(),
                "site" to txtInsertSite.text.toString())
            )
        }
    }
}