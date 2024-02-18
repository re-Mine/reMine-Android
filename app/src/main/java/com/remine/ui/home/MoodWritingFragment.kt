package com.remine.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            requireActivity().onBackPressed()
        }
        return root
    }

}