package com.remine.ui.community.career.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.remine.databinding.ApplyDialogBinding

class ApplyDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }

    private lateinit var binding: ApplyDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ApplyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newFragment = ConfirmDialog()

        binding.ButtonAddSchedule.setOnClickListener {
            // ApplyDialog만 닫을 수 있도록 수정 childFragmentManager -> parentFragmentManager
            newFragment.show(parentFragmentManager, "confirmDialog")
            dismiss()
        }
    }
}