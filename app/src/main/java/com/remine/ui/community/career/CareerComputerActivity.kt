package com.remine.ui.community.career

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.remine.MainActivity
import com.remine.databinding.ActivityCareerComputerBinding
import com.remine.ui.community.career.dialog.ApplyDialog
import com.remine.ui.community.career.dialog.ApplyDialogInterface
import com.remine.ui.community.career.dialog.ConfirmDialog

class CareerComputerActivity : AppCompatActivity(), ApplyDialogInterface {

    private var _binding: ActivityCareerComputerBinding? = null
    private val binding get() = _binding!!
    private var applyDialog: ApplyDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCareerComputerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.ButtonApply.setOnClickListener{
            applyDialog = ApplyDialog(this, this)
            applyDialog?.show()
        }

    }

    // 일정 추가 버튼 클릭
    override fun onAddScheduleBtnClicked() {
        val confirmDialog = ConfirmDialog(this)

        applyDialog?.dismiss()
        confirmDialog.show()
    }
}