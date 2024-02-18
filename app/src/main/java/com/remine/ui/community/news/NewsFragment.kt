package com.remine.ui.community.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remine.R
import com.remine.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 지금 인기있는 소식 리사이클러뷰
        val popularNewsRv : RecyclerView = binding.popularNewsRecyclerView

        val popularNewsItems = ArrayList<String> ()
        popularNewsItems.add("text")

        val popularNewsRvAdapter = PopularNewsRVAdapter(popularNewsItems)
        popularNewsRv.adapter = popularNewsRvAdapter

        // 최근 소식 리사이클러뷰
        val recentNewsRv : RecyclerView = binding.recentNewsRecyclerView

        val recentNewsItems = ArrayList<String> ()
        recentNewsItems.add("text")

        val recentNewsRvAdapter = RecentNewsRVAdapter(recentNewsItems)
        recentNewsRv.adapter = recentNewsRvAdapter


        // 화면 이동
        val newFragment1 = CheckContentsFragment()

        // 소식 확인하기 게시글 화면으로 이동
        binding.buttonMoveToDetailNews.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_news, newFragment1)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 화면 이동
        val newFragment2 = WritePostFragment()

        // 소식 확인하기 게시글 화면으로 이동
        binding.buttonWrite.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_news, newFragment2)
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