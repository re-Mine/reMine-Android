package com.remine.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.remine.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*override fun onCreate(savedInstanceState: Bundle?) {

        val adapter = ReportAdapter(supportFragmentManager)
        adapter.addFragment(WriteReportFragment(), "작성하기")
        adapter.addFragment(MonthlyReportFragment(), "한 달의 기록")
        adapter.addFragment(CreateReportFragment(), "보고서 생성")
        binding.reportViewPager.adapter = adapter
        binding.reportTabLayout.setupWithViewPager(binding.reportViewPager)
    }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapter = ReportAdapter(childFragmentManager, lifecycle)

        adapter.addFragment(WriteReportFragment(), "작성하기")
        adapter.addFragment(MonthlyReportFragment(), "한 달의 기록")
        adapter.addFragment(CreateReportFragment(), "보고서 생성")

        _binding?.reportViewPager?.adapter = adapter

        TabLayoutMediator(binding.reportTabLayout, binding.reportViewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}