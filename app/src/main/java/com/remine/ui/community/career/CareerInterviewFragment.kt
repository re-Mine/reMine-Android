package com.remine.ui.community.career

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.remine.databinding.FragmentCareerInterviewBinding
import com.remine.ui.community.career.dialog.ApplyDialog


class CareerInterviewFragment : Fragment() {

    private var _binding: FragmentCareerInterviewBinding? = null
    private val binding get() = _binding!!
    private var applyDialog: ApplyDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCareerInterviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.ButtonApply.setOnClickListener{

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = FragmentCareerGedBinding.inflate(layoutInflater)
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
    }*/
}