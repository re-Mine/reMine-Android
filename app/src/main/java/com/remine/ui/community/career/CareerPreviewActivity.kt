package com.remine.ui.community.career

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.remine.MainActivity
import com.remine.databinding.ActivityCareerPreviewBinding


class CareerPreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCareerPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_career)

        binding = ActivityCareerPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imagePreviewGed.setOnClickListener{
            val intent = Intent(this, CareerGedActivity::class.java)
            startActivity(intent)
        }

        binding.imagePreviewComputer.setOnClickListener{
            val intent = Intent(this, CareerComputerActivity::class.java)
            startActivity(intent)
        }

        binding.imagePreviewInterview.setOnClickListener{
            val intent = Intent(this, CareerInterviewActivity::class.java)
            startActivity(intent)
        }


    }
}