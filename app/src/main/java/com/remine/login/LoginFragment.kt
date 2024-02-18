package com.remine.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.remine.MainActivity
import com.remine.R
import com.remine.databinding.FragmentLoginBinding
import com.remine.retrofit.RESPONSE_STATE

class LoginFragment : Fragment() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnGoogleLogin.setOnClickListener { // 로그인 버튼
            // 구글 로그인
//            GoogleLoginService().postAuthSignUp { state, jsonElement ->
//                if (state == RESPONSE_STATE.OKAY) {
//                    val intent = Intent(activity, MainActivity::class.java)
//                    startActivity(intent)
//                    activity?.finish()
//                }
//            }
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    return binding.root
    }
}