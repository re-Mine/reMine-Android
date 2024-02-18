package com.remine.ui.declaration

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import com.remine.R
import com.remine.databinding.FragmentDeclartionGuideBinding

class DeclartionGuideFragment : Fragment() {
    private var _binding: FragmentDeclartionGuideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeclartionGuideBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        binding.tvTitle.visibility = View.INVISIBLE
        binding.tvMicGuide.visibility = View.INVISIBLE
        binding.clRecording.visibility = View.INVISIBLE

        binding.ibMic.setOnClickListener {
            val newFragment = DeclarationRecordingFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                android.R.anim.fade_in,  // Fragment가 추가될 때 애니메이션
                android.R.anim.fade_out, // Fragment가 제거될 때 애니메이션
//                android.R.anim.fade_in,  // 백스택에 추가된 Fragment가 추가될 때 애니메이션
//                android.R.anim.fade_out  // 백스택에 추가된 Fragment가 제거될 때 애니메이션
            )
            transaction.replace(R.id.fragment_declaration_base, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.clTodayDecl.setOnClickListener {
            binding.clTodayDecl.visibility = View.INVISIBLE
            binding.clRecording.visibility = View.VISIBLE
            binding.bgMic.visibility = View.INVISIBLE
            binding.tvDeclDescription.visibility = View.INVISIBLE
            binding.tvDeclSentence.visibility = View.INVISIBLE
            binding.ibMic.visibility = View.VISIBLE
            binding.tvMicGuide.visibility = View.VISIBLE

        }
        return root
    }
}