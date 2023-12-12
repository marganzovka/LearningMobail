package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding
import java.lang.Math.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val markAnswer: MarkAnswer = MarkAnswer(this@MainActivity)


        binding.layoutAnswer3.setOnClickListener{
//            if(binding.wrongLayout.isVisible){
//                Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show()
//            }
//            else {
            markAnswer.markAnswerCorrect(
                binding.layoutAnswer3,
                binding.tvVariantNumber3,
                binding.tvVariantValue3)
//            }
            showResultMessage(true)
        }

        binding.layoutAnswer1.setOnClickListener {
            markAnswer.markAnswerWrong(binding.layoutAnswer1,
                binding.tvVariantNumber1,
                binding.tvVariantValue1)
            showResultMessage(false)
        }

        binding.buttonContinue.setOnClickListener {
            markAnswer.markAnswerNetral(
                binding.layoutAnswer3,
                binding.tvVariantNumber3,
                binding.tvVariantValue3)

            markAnswer.markAnswerNetral(
                binding.layoutAnswer1,
                binding.tvVariantNumber1,
                binding.tvVariantValue1)
            binding.resultLayout.isVisible = false
        }
    }



    private fun showResultMessage(isCorrect: Boolean){
        val color: Int
        val messageText: String
        val resultIconResource: Int
        if (isCorrect){
            color = ContextCompat.getColor(this, R.color.green)
            resultIconResource = R.drawable.ic_correct
            messageText = "Correct!" // TODO get from string resoutces
        } else {
            color = ContextCompat.getColor(this, R.color.red)
            resultIconResource = R.drawable.ic_wrong
            messageText = "Wrong!"
        }
        with(binding){
            buttonSkip.isVisible = false
            resultLayout.isVisible = true
            buttonContinue.setTextColor(color)
            resultLayout.setBackgroundColor(color)
            tvResult.text= messageText
            ivResult.setImageResource(resultIconResource)
        }
    }


}