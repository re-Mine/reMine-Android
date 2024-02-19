package com.remine.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

import com.remine.MainActivity
import com.remine.R
import com.remine.databinding.FragmentLoginBinding
// import com.remine.retrofit.RESPONSE_STATE

class LoginFragment : Fragment() {

    var mGoogleSignInClient: GoogleSignInClient? = null
    lateinit var googleLoginLauncher: ActivityResultLauncher<Intent>

    private val RC_SIGN_IN = 9001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.tvLoginBtn.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.btnGoogleLogin.setOnClickListener { // 로그인 버튼

            val signInIntent = mGoogleSignInClient?.signInIntent
            if (signInIntent != null) {
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
//            googleLoginLauncher = registerForActivityResult(StartActivityForResult()) { result ->
//                if (result.resultCode == -1) {
//                    val data = result.data
//                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//                    getGoogleInfo(task)
//                }
//
//            // 구글 로그인
////            GoogleLoginService().postAuthSignUp { state, jsonElement ->
////                if (state == RESPONSE_STATE.OKAY) {
////                    val intent = Intent(activity, MainActivity::class.java)
////                    startActivity(intent)
////                    activity?.finish()
////                }
////            }
//            val intent = Intent(activity, MainActivity::class.java)
//            startActivity(intent)
//            activity?.finish()
            }


        //}

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val providerId = account?.id
            val nickname = account?.displayName
            val email = account?.email
            val profileImgUrl = account?.photoUrl

            // 이메일 또는 필요한 정보를 사용합니다.
            Log.w("login", providerId.toString())
            Log.w("login", nickname.toString())
            Log.w("login", email.toString())
            Log.w("login", profileImgUrl.toString())

            // 로그인 성공 시 작업 수행
            // 예: 사용자 정보를 가져와서 처리

            // 예: 로그인 성공 후 MainActivity로 이동
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()

        } catch (e: ApiException) {
            Log.w("login", "signInResult:failed code=" + e.statusCode)
            // 로그인 실패 시 예외 처리
        }
    }
    fun getGoogleInfo(completedTask: Task<GoogleSignInAccount>) {
        try {
            val TAG = "구글 로그인 결과"
            val account = completedTask.getResult(ApiException::class.java)
            Log.d(TAG, account.id!!)
            Log.d(TAG, account.familyName!!)
            Log.d(TAG, account.givenName!!)
            Log.d(TAG, account.email!!)
        }
        catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    fun googleLogin() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        googleLoginLauncher.launch(signInIntent)
    }
}