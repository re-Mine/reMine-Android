package com.remine.ui.home

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.remine.databinding.FragmentMonthlyReportBinding

class MonthlyReportFragment : Fragment() {

    private var _binding: FragmentMonthlyReportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMonthlyReportBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setTextStyle()

        /** 선형 그래프 **/
        val lineChart = binding.cravingChart

        // 눈금 제거
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.axisRight.setDrawGridLines(false)

        // 데이터 점 추가
        val entries_craving = ArrayList<Entry>()
        entries_craving.add(Entry(1f, 100f))
        entries_craving.add(Entry(2f, 60f))
        entries_craving.add(Entry(3f, 40f))
        entries_craving.add(Entry(4f, 30f))
        entries_craving.add(Entry(5f, 28f))
        entries_craving.add(Entry(6f, 25f))
        entries_craving.add(Entry(7f, 20f))
        entries_craving.add(Entry(8f, 17f))
        entries_craving.add(Entry(9f, 14f))
        entries_craving.add(Entry(10f, 12f))
        entries_craving.add(Entry(11f, 11f))
        entries_craving.add(Entry(12f, 10f))
        entries_craving.add(Entry(13f, 9f))
        entries_craving.add(Entry(14f, 8f))

        // x축 레이블 비활성화
        lineChart.xAxis.setDrawLabels(false)

        // x축 위치 변경
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        // 오른쪽 y축 비활성화
        lineChart.axisLeft.isEnabled = true
        lineChart.axisRight.isEnabled = false

        // 데이터 세트 생성
        val lineDataSet = LineDataSet(entries_craving, " ")

        // 데이터 생성
        val lineData = LineData(lineDataSet)

        // 차트에 데이터 설정
        lineChart.data = lineData

        // 차트 갱신
        lineChart.invalidate()

        // 그래프 색상 변경
        lineDataSet.color = Color.rgb(41, 80, 148)

        // X축 방향으로 2초 동안 애니메이션 적용
        // lineChart.animateX(2000)


        /** 막대 그래프 **/
        val barChart = binding.situationChart

        // 눈금 제거
        barChart.xAxis.setDrawGridLines(false)
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.setDrawGridLines(false)

        // 데이터 점 추가
        val entries_situation = ArrayList<BarEntry>()
        entries_situation.add(BarEntry(1f, 89f))
        entries_situation.add(BarEntry(2f, 63f))
        entries_situation.add(BarEntry(3f, 18f))
        entries_situation.add(BarEntry(4f, 55f))
        entries_situation.add(BarEntry(5f, 10f))

        // x축 위치 변경
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        // 오른쪽 y축 비활성화
        barChart.axisLeft.isEnabled = true
        barChart.axisRight.isEnabled = false

        // Description Label 제거
        barChart.description.isEnabled = false

        // 데이터 세트 생성
        val barDataSet = BarDataSet(entries_situation, "Emotion")

        // 데이터 생성
        val data = BarData(barDataSet)

        // 차트에 데이터 설정
        barChart.data = data

        // 막대 그래프 색상 변경
        val colors = ArrayList<Int>()
        colors.add(Color.rgb(70, 130, 180))
        colors.add(Color.rgb(176, 196, 222))
        colors.add(Color.rgb(220, 220, 220))

        barDataSet.colors = colors

        // X축 방향으로 2초 동안 애니메이션 적용
        barChart.animateX(2000)
        barChart.animateY(2000)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTextStyle() {
        /** 특정 단어의 굵기 변경 **/
        // 레이아웃에 정의된 TextView를 참조
        val textViewCraving = binding.descriptionCraving

        val wholeTextCraving = textViewCraving.text.toString()
        val partsToChangeCraving = listOf("리마인", "24%")
        val spannableStringCraving = SpannableString(wholeTextCraving)

        for (partToChangeCraving in partsToChangeCraving) {
            val styleSpanCraving = StyleSpan(Typeface.BOLD) // 굵은 스타일 지정
            val startIndexOfPartCraving = wholeTextCraving.indexOf(partToChangeCraving) // 변경할 부분의 시작 인덱스
            val endIndexOfPartCraving = startIndexOfPartCraving + partToChangeCraving.length // 변경할 부분의 끝 인덱스

            // SpannableString에 스타일 변경 적용
            spannableStringCraving.setSpan(styleSpanCraving, startIndexOfPartCraving, endIndexOfPartCraving, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        textViewCraving.text = spannableStringCraving // TextView에 적용

        // 레이아웃에 정의된 TextView를 참조
        val textViewSituation = binding.descriptionSituation

        val wholeTextSituation = textViewSituation.text.toString()
        val partsToChangeSituation = listOf("분노", "피로", "힘듦")
        val spannableStringSituation = SpannableString(wholeTextSituation)

        for (partToChangeSituation in partsToChangeSituation) {
            val styleSpanSituation = StyleSpan(Typeface.BOLD) // 굵은 스타일 지정
            val startIndexOfPartSituation = wholeTextSituation.indexOf(partToChangeSituation) // 변경할 부분의 시작 인덱스
            val endIndexOfPartSituation = startIndexOfPartSituation + partToChangeSituation.length // 변경할 부분의 끝 인덱스

            // SpannableString에 스타일 변경 적용
            spannableStringSituation.setSpan(styleSpanSituation, startIndexOfPartSituation, endIndexOfPartSituation, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        textViewSituation.text = spannableStringSituation // TextView에 적용
    }

}