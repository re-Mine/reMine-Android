package com.remine.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.viewpager2.widget.ViewPager2
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.remine.R
import com.remine.databinding.FragmentHomeBinding
import com.remine.databinding.FragmentWriteReportBinding

class WriteReportFragment : Fragment() {

    private var _binding: FragmentWriteReportBinding? = null
    private var _homeBinding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeBinding get() = _homeBinding

    private lateinit var calendarView: MaterialCalendarView
    var selectDate : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWriteReportBinding.inflate(inflater, container, false)
        calendarView = binding.calendar

        val root: View = binding.root

        calendarView.selectedDate = CalendarDay.today()
        selectDate = String.format("%04d년 %02d월 %02d일", CalendarDay.today().year, CalendarDay.today().month + 1, CalendarDay.today().day)
        binding.tvDate.text = selectDate
        binding.tvDate2.text = selectDate

        calendarView.setOnDateChangedListener { widget, date, selected ->
            if (selected) {
                calendarView.currentDate = calendarView.selectedDate
                // 선택된 날짜를 YYYY-MM-DD 형식의 문자열로 변환합니다.
                selectDate = String.format("%04d년 %02d월 %02d일", date.year, date.month + 1, date.day)

                binding.tvDate.text = selectDate
                binding.tvDate2.text = selectDate
                // API를 호출하는 함수를 실행합니다.
                //viewModel.fetchReservations(giftId,date)
            }}
//
//        val viewPager = homeBinding?.reportViewPager
//        val screenHeight = resources.displayMetrics.heightPixels
//        val layoutParams = viewPager?.layoutParams
//        layoutParams?.height = (screenHeight * 0.7).toInt()
//        viewPager?.layoutParams = layoutParams

        binding.clToday.setOnClickListener {
            //calendarView.currentDate = selectDate.toString()
            val bundle = Bundle()
            bundle.putString("date",selectDate)
            val newFragment = TodayWritingFragment()
            newFragment.arguments = bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_home, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.clMood.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("date",selectDate)
            val newFragment = MoodWritingFragment()
            newFragment.arguments = bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_home, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}