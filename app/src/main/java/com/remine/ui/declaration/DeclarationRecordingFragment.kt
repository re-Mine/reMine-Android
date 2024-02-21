package com.remine.ui.declaration

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.remine.R
import com.remine.databinding.FragmentDeclarationRecordingBinding
import com.remine.retrofit.RESPONSE_STATE
import com.remine.retrofit.RetrofitManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.util.Date
import kotlin.random.Random

private const val ARG_PARAM1 = "isGuide"

private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

private val animations = mutableListOf<Animation>()


class DeclarationRecordingFragment : Fragment() {
    private var fileName: String = ""

    //private var recordButton: RecordButton? = null
    private var recorder: MediaRecorder? = null
    private var isRecording = false

    private var outputPath: String? = null
    private var mediaRecorder : MediaRecorder? = null
    private var state : Boolean = false


    //private var playButton: PlayButton? = null
    private var player: MediaPlayer? = null

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private val permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    private var isGuide: String? = null
    private var participants: Int? = null
    private var membername: String? = null

    private var _binding: FragmentDeclarationRecordingBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        isGuide = arguments?.getString(ARG_PARAM1)
//        participants = arguments?.getInt("participants")
//        membername = arguments?.getString("memberName")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeclarationRecordingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setTextColor()

        val rectangles = listOf(binding.rectangle1, binding.rectangle2, binding.rectangle3, binding.rectangle4)
        RetrofitManager.instance.getDeclarations(){
                state, result ->
            when(state){
                RESPONSE_STATE.OKAY -> {
                    val resultData = result?.result

//                    var timeText = SpannableStringBuilder(binding.tvTop.text.toString())
//                    val startIndex2 = 0
//                    val endIndex2 = timeText.indexOf("님")
//                    timeText.replace(startIndex2, endIndex2, resultData?.memberName)
//                    binding.tvTop.text = timeText

                    val timeText = SpannableStringBuilder(binding.tvDeclPeople.text.toString())
                    val startIndex = timeText.indexOf("오늘도")+4
                    val endIndex = timeText.indexOf("명")
                    timeText.replace(startIndex, endIndex, resultData?.todayParticipantsCount.toString())

                    binding.tvDeclPeople.text = timeText

                    setTextColor()
                    Log.d("retrofit", "DeclarationMainFragment - onCreateView() called / 선언 조회 성공 ${result.toString()}")
                }
                RESPONSE_STATE.FAIL -> {
                    Log.d("retrofit", "DeclarationMainFragment - onCreateView() called / 선언 조회 실패")
                }

                else -> {}
            }
        }

        fileName = "${requireContext().filesDir}/audiorecordtest.3gp"
        //fileName = Date().time.toString()+".mp3"
        RetrofitManager.instance.getDeclarations(){
                state, result ->
            when(state){
                RESPONSE_STATE.OKAY -> {
                    val resultData = result?.result

                    var timeText = SpannableStringBuilder(binding.tvTop.text.toString())
                    val startIndex2 = 0
                    val endIndex2 = timeText.indexOf("님")
                    timeText.replace(startIndex2, endIndex2, resultData?.memberName)
                    binding.tvTop.text = timeText

                    timeText = SpannableStringBuilder(binding.tvDeclPeople.text.toString())
                    val startIndex = timeText.indexOf("오늘도")+4
                    val endIndex = timeText.indexOf("명")
                    timeText.replace(startIndex, endIndex, resultData?.todayParticipantsCount.toString())

                    binding.tvDeclPeople.text = timeText

                    setTextColor()
                    Log.d("retrofit", "DeclarationMainFragment - onCreateView() called / 선언 조회 성공 ${result.toString()}")
                }
                RESPONSE_STATE.FAIL -> {
                    Log.d("retrofit", "DeclarationMainFragment - onCreateView() called / 선언 조회 실패")
                }

                else -> {}
            }
        }

        fileName = "${requireContext().filesDir}/audiorecordtest.3gp"
        //fileName = Date().time.toString()+".mp3"

        ActivityCompat.requestPermissions(super.requireActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        //recordButton = RecordButton(super.requireContext())
        //playButton = PlayButton(super.requireContext())
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
//            binding.ibMic.visibility = View.INVISIBLE
//            binding.ibMicStop.visibility = View.VISIBLE

            // TextView의 가시성을 GONE으로 설정
            if (binding.clDecl1.visibility == View.VISIBLE) {
                binding.clDecl1.visibility = View.GONE
                binding.tvDeclDescription.text = "듣는 중..."

                val transition = AutoTransition() // 애니메이션 효과 생성
                transition.duration = 400 // 애니메이션 지속 시간 설정 (ms)
                // 변경된 ConstraintSet을 적용하여 애니메이션 실행
                TransitionManager.beginDelayedTransition(binding.clRecording, transition)
            }

            if (isRecording) {
                stopRecording()
                binding.tvDeclDescription.text = "녹음 완료"
                binding.clAnimation.visibility = View.INVISIBLE
                stopRecording()
                animations.forEach { it.cancel() }
            } else {
                startRecording()
                binding.tvDeclDescription.text = "듣는 중..."

                binding.clAnimation.visibility = View.VISIBLE
                rectangles.forEach { rectangle ->
                    val randomHeight = Random.nextInt(80, 300) // 랜덤 높이 설정
                    val animation = ResizeAnimation(rectangle, 30, randomHeight)
                    animation.duration = 100000 // 애니메이션 지속 시간 설정 (ms)
                    rectangle.startAnimation(animation)
                    animations.add(animation)
                }


            }

            //recordButton!!.performClick()

            Log.d("ibMic", "clicked")
            //Log.d("recordButton", "${recordButton!!.mStartRecording}")
        }

        binding.ibMicStop.setOnClickListener {
            binding.ibMicStop.visibility = View.GONE
            binding.ibMic.visibility = View.VISIBLE
            binding.tvDeclDescription.text = " "
            binding.clAnimation.visibility = View.INVISIBLE
            stopRecording()
            animations.forEach { it.cancel() } // 모든 애니메이션을 중지
        }
        // Inflate the layout for this fragment
        return root
    }
    private fun startRecording() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val fileName: String = Date().time.toString()+".mp3"
            outputPath =
                Environment.getExternalStorageDirectory().absolutePath + "/Download/" + fileName //내장메모리 밑에 위치
            mediaRecorder = MediaRecorder()
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder?.setOutputFile(outputPath)
            try {
                mediaRecorder?.prepare()
                mediaRecorder?.start()
                state = true
                isRecording = true
                binding.ibMic.setImageResource(R.drawable.btn_stop)
                Toast.makeText(requireContext(), "녹음이 시작되었습니다.", Toast.LENGTH_SHORT).show()
            } catch (e: IllegalStateException){
                e.printStackTrace()
            } catch (e: IOException){
                e.printStackTrace()
            }

//            recorder = MediaRecorder().apply {
//                setAudioSource(MediaRecorder.AudioSource.MIC)
//                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
//                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
//                setOutputFile(outputPath)
//                try {
//                    recorder?.prepare()
//                } catch (e: IOException) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Failed to prepare recorder",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                recorder?.start()
//                isRecording = true
//                binding.ibMic.setImageResource(R.drawable.btn_stop)
//                Toast.makeText(requireContext(), "Recording started", Toast.LENGTH_SHORT).show()
//            }
        }
            else {
            requestPermissions(permissions, REQUEST_RECORD_AUDIO_PERMISSION)
        }

    }

    private fun stopRecording() {
        if (isRecording){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            state = false
            isRecording = false
            binding.ibMic.setImageResource(R.drawable.btn_mic)

            //val body = MultipartBody.Part.createFormData("file", file.name, requestFile)  //파일이름, 파일
            //val filedata: IRetrofit.Filedata = IRetrofit.Filedata(file.name, requestFile)

            val file = File(outputPath)

            val requestFile = RequestBody.create("audio/*".toMediaTypeOrNull(), file)

            val filePart = MultipartBody.Part.createFormData("file", fileName, RequestBody.create("audio/*".toMediaTypeOrNull(), file))

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("content", "fileName")
                .build()

            RetrofitManager.instance.postDeclarations(
                filedata = filePart,
                content = requestBody
            ) { responseState ->
                when (responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d("retrofit", "postDeclarations api : ${responseState}")
                    }

                    RESPONSE_STATE.FAIL -> {
                        Log.d("retrofit", "postDeclarations api 호출 에러")
                    }
                }
            }

            Toast.makeText(requireContext(), "녹음이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "녹음 상태가 아닙니다.", Toast.LENGTH_SHORT).show()
        }
//        if (recorder != null && isRecording) {
//            try {
//                recorder?.stop()
//                recorder?.release()
//                recorder = null
//                isRecording = false
//                binding.ibMic.setImageResource(R.drawable.btn_mic)
//                Toast.makeText(requireContext(), "Recording stopped", Toast.LENGTH_SHORT).show()
//            } catch (e: IllegalStateException) {
//                Toast.makeText(requireContext(), "Failed to stop recording", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            Toast.makeText(requireContext(), "Recording is not in progress", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if (isGuide == "true") {
//            isGuide = "false"
//            //showGuidance()
//        }
    }

    private fun setTextColor() {
        val coloredTitleText = binding.tvTop.text.toString()
        val timeText = SpannableStringBuilder(coloredTitleText)
        var startIndex = timeText.indexOf("선언")
        var endIndex = startIndex + "선언".length
        var color = Color.parseColor("#4285F4") // 리소스에서 색상 가져오기
        timeText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        startIndex = timeText.indexOf("다짐")
        endIndex = startIndex + "다짐".length
        color = Color.parseColor("#EA4335") // 리소스에서 색상 가져오기
        timeText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvTop.text = timeText
    }


    private fun showGuidance() {
        val newFragment2 = DeclartionGuideFragment()
        val transaction2 = requireActivity().supportFragmentManager.beginTransaction()
        transaction2.setCustomAnimations(
            android.R.anim.fade_in,  // Fragment가 추가될 때 애니메이션
            android.R.anim.fade_out, // Fragment가 제거될 때 애니메이션
            android.R.anim.fade_in,  // 백스택에 추가된 Fragment가 추가될 때 애니메이션
            android.R.anim.fade_out  // 백스택에 추가된 Fragment가 제거될 때 애니메이션
        )
        transaction2.replace(R.id.fragment_declaration_base, newFragment2)
        transaction2.addToBackStack("DeclarationRecordingFragment")
        transaction2.commit()
        //binding.layoutDeclarationRecording.background = ColorDrawable(Color.parseColor("#D86F86AC"))
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
//            grantResults[0] == PackageManager.PERMISSION_GRANTED
//        } else {
//            false
//        }
//        if (!permissionToRecordAccepted) return //finish() 대신 return씀..(임시방편....)
//    }


    /*
    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }



    private fun startRecording() {
        recorder = MediaRecorder().apply {
            Log.d("recorder", "startRecording()")
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
            Log.d("recorder", "stopRecording()")
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
*/
//    internal inner class PlayButton(ctx: Context) : androidx.appcompat.widget.AppCompatButton(ctx) {
//        var mStartPlaying = true
//        var clicker: OnClickListener = OnClickListener {
//            onPlay(mStartPlaying)
//            text = when (mStartPlaying) {
//                true -> "Stop playing"
//                false -> "Start playing"
//            }
//            mStartPlaying = !mStartPlaying
//        }
//
//        init {
//            text = "Start playing"
//            setOnClickListener(clicker)
//        }
//    }

}