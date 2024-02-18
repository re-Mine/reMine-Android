package com.remine.ui.community.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remine.R
import com.remine.databinding.FragmentCheckContentsBinding

class CheckContentsFragment : Fragment() {

    private var _binding: FragmentCheckContentsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckContentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 댓글 리사이클러뷰
        val commentsRv : RecyclerView = binding.commentsRecyclerView

        val commentsItems = ArrayList<String> ()
        commentsItems.add("text")

        val commentsRvAdapter = CommentsRVAdapter(commentsItems)
        commentsRv.adapter = commentsRvAdapter

        // 화면 이동
        val newFragment = WritePostFragment()

        // 소식 확인하기 게시글 화면으로 이동
        binding.buttonWrite.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_check_contents, newFragment)
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