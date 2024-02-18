package com.remine.ui.declaration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.remine.R

class DeclarationBaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_declaration_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 초기화면으로 첫 번째 프래그먼트를 표시
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_declaration_base, DeclarationMainFragment())
            .addToBackStack(null)
            .commit()
    }

//    // 첫 번째 프래그먼트에서 다른 프래그먼트로 이동하는 함수
//    fun navigateToOtherFragment() {
//        childFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, OtherFragment())
//            .addToBackStack(null) // 백 스택에 추가하지 않음
//            .commit()
//    }
}