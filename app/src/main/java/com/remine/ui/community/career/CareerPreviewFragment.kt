package com.remine.ui.community.career

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.remine.R
import com.remine.databinding.FragmentCareerPreviewBinding
import com.remine.ui.community.news.NewsFragment


class CareerPreviewFragment : Fragment() {

    private var _binding: FragmentCareerPreviewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentCareerPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 검정고시 페이지로 이동
        val newFragment1 = CareerGedFragment()

        binding.imagePreviewGed.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_career_preview, newFragment1)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 컴퓨터 문서 기초 과정 페이지로 이동

        val newFragment2 = CareerComputerFragment()

        binding.imagePreviewComputer.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_career_preview, newFragment2)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 취업 면접 준비반 페이지로 이동

        val newFragment3 = CareerInterviewFragment()

        binding.imagePreviewInterview.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_career_preview, newFragment3)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_career)

        binding = ActivityCareerPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imagePreviewGed.setOnClickListener{
            val intent = Intent(this, CareerGedFragment::class.java)
            startActivity(intent)
        }

        binding.imagePreviewComputer.setOnClickListener{
            val intent = Intent(this, CareerComputerFragment::class.java)
            startActivity(intent)
        }

        binding.imagePreviewInterview.setOnClickListener{
            val intent = Intent(this, CareerInterviewFragment::class.java)
            startActivity(intent)
        }


    }*/
}