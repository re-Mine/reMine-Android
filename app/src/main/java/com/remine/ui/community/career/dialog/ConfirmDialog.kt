package com.remine.ui.community.career.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.remine.databinding.ConfirmDialogBinding

class ConfirmDialog (context: Context) : Dialog(context) {

    private var _binding : ConfirmDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ConfirmDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}