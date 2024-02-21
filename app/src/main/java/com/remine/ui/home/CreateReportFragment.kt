package com.remine.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.remine.R
import com.remine.databinding.FragmentCreateReportBinding
import com.remine.ui.community.news.PopularNewsRVAdapter

class CreateReportFragment : Fragment() {

    private var _binding: FragmentCreateReportBinding? = null
    private val binding get() = _binding!!

    private var isSpinnerFirstCall = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateReportBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 기관 리사이클러뷰
        val centerRv : RecyclerView = binding.nearCenterRecyclerView

        val nearCenterItems = ArrayList<String> ()
        nearCenterItems.add("text")

        val nearCenterRvAdapter = NearCenterRVAdapter(nearCenterItems)
        centerRv.adapter = nearCenterRvAdapter

        // 치료 보고서에 들어가는 내용 확인 버튼 클릭시 이벤트
        val newFragment = MonthlyReportFragment()

        binding.buttonCheckReport.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_create_report, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        // 스피너 선택시 이벤트
        /*
        binding.spinnerCenter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // 첫 번째 호출 값 무시
                if (isSpinnerFirstCall) {
                    isSpinnerFirstCall = false
                } else {
                    val selectedItem = parent?.getItemAtPosition(position).toString()

                    if (selectedItem == "기관 선택") {
                        Toast.makeText(context, "기관을 선택해주세요!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "선택된 기관: $selectedItem", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing selected
            }
        }
        */

        // 생성 버튼 클릭시 이벤트
        binding.buttonCreateReport.setOnClickListener {
            val selectedItem = binding.spinnerCenter.selectedItem.toString()

            if (selectedItem == "기관 선택") {
                Toast.makeText(context, "기관을 선택해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "보고서가 생성되었습니다!", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}