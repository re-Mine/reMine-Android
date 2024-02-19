package com.remine.ui.community.career

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.remine.databinding.FragmentCareerGedBinding
import com.remine.ui.community.career.dialog.ApplyDialog

class CareerGedFragment : Fragment() {

    private var _binding: FragmentCareerGedBinding? = null
    private val binding get() = _binding!!
    private var applyDialog: ApplyDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCareerGedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 다이얼로그 띄우기
        val newFragment = ApplyDialog()

        binding.ButtonApply.setOnClickListener{
            newFragment.show(childFragmentManager, "applyDialog")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}