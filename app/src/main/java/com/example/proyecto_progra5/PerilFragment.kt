package com.example.proyecto_progra5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.proyecto_progra5.databinding.FragmentPerfilBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_perfil.*


class PerilFragment : Fragment() {
    private val TAG = "LoginActivity"
    private val RC_GOOGLE_SIGN_IN = 4926
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the Layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.isVisible = true
        btnLogout.isVisible = false
        imageViewLogin.isVisible = false
        lblEmail.isVisible = false
        auth = Firebase.auth
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        btnLogin.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(
                signInIntent,
                RC_GOOGLE_SIGN_IN
            )
            //btnAddSite.show()
        }
        btnLogout.setOnClickListener {
            googleSignInClient.signOut()
            Firebase.auth.signOut()
            lblEmail.text = "Welcome"
            imageViewLogin.setImageResource(0)
            btnLogin.isVisible = true
            btnLogout.isVisible = false
            imageViewLogin.isVisible = false
            lblEmail.isVisible = false
            btnAddSite.isVisible = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(
                    TAG,
                    "firebaseAuthWithGoogle:" + account.id
                )
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    if (user != null) {
                        lblEmail.text = user.email.toString()
                        Picasso.get()
                            .load(user.photoUrl)
                            .into(imageViewLogin)
                        btnLogin.isVisible = false
                        btnLogout.isVisible = true
                        imageViewLogin.isVisible = true
                        lblEmail.isVisible = true
                    }
                    updateUI(user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(requireActivity(), "Authentication failed", Toast.LENGTH_SHORT)
                        .show()
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            lblEmail.text = currentUser.email.toString()
            Picasso.get()
                .load(currentUser.photoUrl)
                .into(imageViewLogin)
            btnLogin.isVisible = false
            btnLogout.isVisible = true
            imageViewLogin.isVisible = true
            lblEmail.isVisible = true
        }
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            Log.w(TAG, "user not signed in..")
            return
        }
    }

}





