package com.remine.ui.community.career

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.remine.MainActivity
import com.remine.databinding.ActivityCareerGedBinding
import com.remine.ui.community.career.dialog.ApplyDialog
import com.remine.ui.community.career.dialog.ApplyDialogInterface
import com.remine.ui.community.career.dialog.ConfirmDialog

class CareerGedActivity : AppCompatActivity(), ApplyDialogInterface {

    private var _binding: ActivityCareerGedBinding? = null
    private val binding get() = _binding!!
    private var applyDialog: ApplyDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCareerGedBinding.inflate(layoutInflater)
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

    // 일정 추가 버튼 클릭시 이벤트 - 이전 다이얼로그 종료, 새 다이얼로그 시작
    override fun onAddScheduleBtnClicked() {
        val confirmDialog = ConfirmDialog(this)

        applyDialog?.dismiss()
        confirmDialog.show()
    }
}