package com.remine.ui.declaration

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.remine.R
import com.remine.databinding.FragmentDeclarationRecordingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeclarationRecordingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeclarationRecordingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentDeclarationRecordingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeclarationRecordingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.ibMic.setOnClickListener {
            binding.clDecl1.visibility = View.GONE
            binding.tvDeclDescription.text = "듣는 중..."
        }
        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DeclarationRecordingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeclarationRecordingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}