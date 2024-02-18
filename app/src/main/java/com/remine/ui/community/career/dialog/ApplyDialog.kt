package com.remine.ui.community.career.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.remine.databinding.ApplyDialogBinding

class ApplyDialog(context: Context, applyDialogInterface: ApplyDialogInterface) : Dialog(context) {

    private var _binding : ApplyDialogBinding? = null
    private val binding get() = _binding!!

    private var applyDialogInterface:ApplyDialogInterface? = null

    // 인터페이스 연결
    init {
        this.applyDialogInterface = applyDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ApplyDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonAddSchedule.setOnClickListener{
            this.applyDialogInterface?.onAddScheduleBtnClicked()
        }
    }
}