package com.remine.ui.declaration

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.remine.R
import com.remine.databinding.FragmentDeclarationBinding

class DeclarationFragment : Fragment() {

    private var _binding: FragmentDeclarationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

//    val declarationViewModel =
//        ViewModelProvider(this).get(DeclarationViewModel::class.java)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeclarationBinding.inflate(inflater, container, false)
        val root: View = binding.root

       // val textView: TextView = binding.textDeclaration
//        declarationViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        // 특정 글자를 다른 색상으로 변경하기 위해 SpannableStringBuilder 사용
        val coloredTitleText = binding.tvTop.text.toString()
        var spannableText = SpannableStringBuilder(coloredTitleText)
        var startIndex = spannableText.indexOf("선언")
        var endIndex = startIndex + "선언".length
        var color = Color.parseColor("#4285F4") // 리소스에서 색상 가져오기
        spannableText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        startIndex = spannableText.indexOf("다짐")
        endIndex = startIndex + "다짐".length
        color = Color.parseColor("#EA4335") // 리소스에서 색상 가져오기
        spannableText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvTop.text = spannableText

        val coloredPastText = binding.tvDeclDescription.text.toString()
        spannableText = SpannableStringBuilder(coloredPastText)
        startIndex = spannableText.indexOf("지난")+2
        endIndex = spannableText.indexOf("일동안")
        color = Color.parseColor("#34A853")
        spannableText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        startIndex = spannableText.indexOf("총 ")+1
        endIndex = spannableText.indexOf("을")
        color = Color.parseColor("#EA4335")
        spannableText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvDeclDescription.text = spannableText


        binding.btnDeclaration.setOnClickListener {
            val newFragment = DeclarationRecordingFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_declaration, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            //it.findNavController().navigate(R.id.action_declarationFragment_to_declarationRecordingFragment)
            
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val declarationList = mutableListOf(
            Declaration("1월 5일", "내 삶의 주인공은\n나다"),
            Declaration("1월 8일", "내일을 위해\n오늘의 선택에\n헌신하자"),
            Declaration("1월 10일", "최고의 버전으로\n변화하며,\n삶의 아름다움을\n찾아가자."),
        )

        binding.rvDeclaration.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = DeclarationAdapter(declarationList)
        binding.rvDeclaration.adapter = adapter

//        declarationViewModel.declarationList.observe(viewLifecycleOwner, { declarationList ->
//            adapter.submitList(declarationList)
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}