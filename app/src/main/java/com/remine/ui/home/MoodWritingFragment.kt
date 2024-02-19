package com.remine.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.remine.R
import com.remine.databinding.FragmentMoodWritingBinding

private const val ARG_PARAM = "date"

class MoodWritingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var date: String? = null
    private var _binding: FragmentMoodWritingBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            date = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMoodWritingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tvDate.text = date

        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.tvSave.setOnClickListener {
//            requireActivity().findViewById<TextView>(R.id.tv_diary).text = binding.etDiary.text
            val text = binding.tvRating.text.toString() + " : " +binding.ratingBar.rating.toString() + "\n" + binding.tvRating2.text.toString() + " : " +binding.ratingBar2.rating.toString() + "\n" + binding.tvRating3.text.toString() + " : " +binding.ratingBar3.rating.toString()+ "\n" + binding.tvRating4.text.toString() + " : " +binding.ratingBar4.rating.toString()+ "\n" + binding.tvRating5.text.toString() + " : " +binding.ratingBar5.rating.toString()+ "\n" + binding.tvRating6.text.toString() + " : " +binding.ratingBar6.rating.toString()

            requireActivity().findViewById<TextView>(R.id.tv_diary2).text = text
            requireActivity().onBackPressed()
        }
        return root
    }

}