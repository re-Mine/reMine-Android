package com.remine.ui.declaration

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.remine.R
import com.remine.databinding.FragmentDeclarationRecordingBinding
import java.io.IOException

private const val ARG_PARAM1 = "isGuide"

private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class DeclarationRecordingFragment : Fragment() {
    private var fileName: String = ""

    private var recordButton: RecordButton? = null
    private var recorder: MediaRecorder? = null

    private var playButton: PlayButton? = null
    private var player: MediaPlayer? = null

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    private var isGuide: String? = null

    private var _binding: FragmentDeclarationRecordingBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isGuide = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeclarationRecordingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fileName = "${requireContext().filesDir}/audiorecordtest.3gp"

        ActivityCompat.requestPermissions(super.requireActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        recordButton = RecordButton(super.requireContext())
        playButton = PlayButton(super.requireContext())
//        val ll = LinearLayout(super.requireContext()).apply {
//            addView(recordButton,
//                LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    0f))
//            addView(playButton,
//                LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    0f))
//        }
//        binding.clRecording.addView(ll)

        binding.ibMic.setOnClickListener {
            val transition = AutoTransition() // 애니메이션 효과 생성
            transition.duration = 400 // 애니메이션 지속 시간 설정 (ms)

            // TextView의 가시성을 GONE으로 설정
            binding.clDecl1.visibility = View.GONE

            // 변경된 ConstraintSet을 적용하여 애니메이션 실행
            TransitionManager.beginDelayedTransition(binding.clRecording, transition)
            binding.tvDeclDescription.text = "듣는 중..."
            recordButton!!.performClick()

            Log.d("ibMic", "clicked")
            Log.d("recordButton", "${recordButton!!.mStartRecording}")
        }
        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isGuide == "true") {
            isGuide = "false"
            showGuidance()
//            val newFragment2 = DeclartionGuideFragment()
//            val transaction2 = requireActivity().supportFragmentManager.beginTransaction()
//            transaction2.setCustomAnimations(
//                android.R.anim.fade_in,  // Fragment가 추가될 때 애니메이션
//                android.R.anim.fade_out, // Fragment가 제거될 때 애니메이션
//                android.R.anim.fade_in,  // 백스택에 추가된 Fragment가 추가될 때 애니메이션
//                android.R.anim.fade_out  // 백스택에 추가된 Fragment가 제거될 때 애니메이션
//            )
//            transaction2.replace(R.id.fragment_declaration, newFragment2)
//            transaction2.addToBackStack("DeclarationRecordingFragment")
//            transaction2.commit()
        }

        // 특정 글자를 다른 색상으로 변경하기 위해 SpannableStringBuilder 사용
        val coloredTitleText = binding.tvTop.text.toString()
        var spannableText = SpannableStringBuilder(coloredTitleText)
        var startIndex = spannableText.indexOf("선언")
        var endIndex = startIndex + "선언".length
        var color = Color.parseColor("#4285F4") // 리소스에서 색상 가져오기
        spannableText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        startIndex = spannableText.indexOf("다짐")
        endIndex = startIndex + "다짐".length
        color = Color.parseColor("#EA4335") // 리소스에서 색상 가져오기
        spannableText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvTop.text = spannableText
    }

    private fun showGuidance() {
        //binding.layoutDeclarationRecording.background = ColorDrawable(Color.parseColor("#D86F86AC"))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted) return //finish() 대신 return씀..(임시방편....)
    }

    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }

    private fun onPlay(start: Boolean) = if (start) {
        startPlaying()
    } else {
        stopPlaying()
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed ${e.message}")
            }

            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    internal inner class RecordButton(ctx: Context) : androidx.appcompat.widget.AppCompatButton(ctx) {

        var mStartRecording = true

        var clicker: OnClickListener = OnClickListener {
            onRecord(mStartRecording)
            text = when (mStartRecording) {
                true -> "Stop recording"
                false -> "Start recording"
            }
            mStartRecording = !mStartRecording
        }

        init {
            text = "Start recording"
            setOnClickListener(clicker)
        }
    }

    internal inner class PlayButton(ctx: Context) : androidx.appcompat.widget.AppCompatButton(ctx) {
        var mStartPlaying = true
        var clicker: OnClickListener = OnClickListener {
            onPlay(mStartPlaying)
            text = when (mStartPlaying) {
                true -> "Stop playing"
                false -> "Start playing"
            }
            mStartPlaying = !mStartPlaying
        }

        init {
            text = "Start playing"
            setOnClickListener(clicker)
        }
    }

}