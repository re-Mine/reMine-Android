package com.remine.ui.declaration

import android.app.Activity
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.remine.R
import com.remine.databinding.FragmentDeclarationMainBinding

class DeclarationMainFragment : Fragment() {

    private var _binding: FragmentDeclarationMainBinding? = null

    private val binding get() = _binding!!

//    val declarationViewModel =
//        ViewModelProvider(this).get(DeclarationViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeclarationMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        getStandardSize()
        setTextColor()

        val layoutParams = binding.constraintLayout.layoutParams
        layoutParams.height = screenSize_Y / 2 + 80
        binding.constraintLayout.layoutParams = layoutParams

        binding.btnDeclaration.setOnClickListener {
            val newFragment = DeclarationRecordingFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                android.R.anim.fade_in,  // Fragment가 추가될 때 애니메이션
                android.R.anim.fade_out, // Fragment가 제거될 때 애니메이션
                android.R.anim.fade_in,  // 백스택에 추가된 Fragment가 추가될 때 애니메이션
                android.R.anim.fade_out  // 백스택에 추가된 Fragment가 제거될 때 애니메이션
            )
            transaction.replace(R.id.fragment_declaration_base, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            //it.findNavController().navigate(R.id.action_declarationFragment_to_declarationRecordingFragment)
            
        }

        binding.btnHowToDeclaration.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("isGuide", "true")
            val newFragment = DeclartionGuideFragment()
            newFragment.arguments = bundle
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                android.R.anim.fade_in,  // Fragment가 추가될 때 애니메이션
                android.R.anim.fade_out, // Fragment가 제거될 때 애니메이션
                android.R.anim.fade_in,  // 백스택에 추가된 Fragment가 추가될 때 애니메이션
                android.R.anim.fade_out  // 백스택에 추가된 Fragment가 제거될 때 애니메이션
            )

            //transaction.add(R.id.fragment_declaration_base, newFragment)
            transaction.replace(R.id.fragment_declaration_base, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()

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

    fun navigateToOtherFragment() {
        childFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,  // Fragment가 추가될 때 애니메이션
                android.R.anim.fade_out, // Fragment가 제거될 때 애니메이션
                android.R.anim.fade_in,  // 백스택에 추가된 Fragment가 추가될 때 애니메이션
                android.R.anim.fade_out  // 백스택에 추가된 Fragment가 제거될 때 애니메이션)
            )
            .replace(R.id.fragment_declaration_base, DeclarationRecordingFragment())
            .addToBackStack(null) // 백 스택에 추가하지 않음
            .commit()
    }
    private fun setTextColor() {
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
        //binding.tvTop.setTextSize(standardSize_X.toFloat() / 15)

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
        //binding.tvDeclDescription.setTextSize(standardSize_X.toFloat() / 17)
    }

    fun getScreenSize(activity: Activity): Point? {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }
    var standardSize_X:Int = 0
    var standardSize_Y:Int = 0

    var screenSize_X:Int = 0
    var screenSize_Y:Int = 0

    var density = 0f

    fun getStandardSize() {
        val ScreenSize = getScreenSize(super.requireActivity())
        density = resources.displayMetrics.density
        screenSize_X = ScreenSize!!.x
        screenSize_Y = ScreenSize.y
        standardSize_X = (ScreenSize!!.x / density).toInt()
        standardSize_Y = (ScreenSize.y / density).toInt()
    }
}

