package com.remine.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.remine.R
import com.remine.databinding.FragmentCommunityBinding
import com.remine.ui.community.career.CareerPreviewFragment
import com.remine.ui.community.career.CareerRVAdapter
import com.remine.ui.community.news.NewsFragment
import com.remine.ui.community.news.PopularNewsRVAdapter

class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val communityViewModel =
            ViewModelProvider(this).get(CommunityViewModel::class.java)

        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 커리어 데이터 리사이클러뷰
        val careerRv : RecyclerView = binding.careerRecyclerView

        val carrerItems = ArrayList<String>()
        carrerItems.add("image")

        val careerRvAdapter = CareerRVAdapter(carrerItems)
        careerRv.adapter = careerRvAdapter

        // 소식 확인하기 리사이클러뷰
        val newsRv : RecyclerView = binding.popularNewsRecyclerView

        val newsItems = ArrayList<String> ()
        newsItems.add("text")

        val newsRvAdapter = PopularNewsRVAdapter(newsItems)
        newsRv.adapter = newsRvAdapter

        val newFragment1 = CareerPreviewFragment()

        binding.buttonMoveToCareer.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_community, newFragment1)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 프래그먼트 화면 전환 - 소식 확인하기 페이지로 이동
        // val bundle = Bundle()
        val newFragment2 = NewsFragment()

        // newFragment.arguments = bundle

        binding.buttonCheckNews.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_community, newFragment2)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.buttonMoveToNews.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_community, newFragment2)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 뷰페이저2
        binding.viewPagerDeclaration.adapter = NewsDeclarationViewPagerAdapter(getDeclarationList(), requireContext()) // 어댑터 생성
        // 가로 방향
        binding.viewPagerDeclaration.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicatorDeclaration.setViewPager(binding.viewPagerDeclaration)

        return root
    }

    private fun getDeclarationList(): ArrayList<Int> {
        return arrayListOf<Int>(R.layout.declaration_list_item1, R.layout.declaration_list_item2, R.layout.declaration_list_item3)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}