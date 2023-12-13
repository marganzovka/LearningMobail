package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val markAnswer: MarkAnswer = MarkAnswer(this@MainActivity)

    private lateinit var first: MarkAnswer.LayoutAnswer
    private lateinit var second: MarkAnswer.LayoutAnswer
    private lateinit var third: MarkAnswer.LayoutAnswer
    private lateinit var fourth: MarkAnswer.LayoutAnswer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        first = MarkAnswer.LayoutAnswer(binding.layoutAnswer1, binding.tvVariantNumber1, binding.tvVariantValue1)
        second = MarkAnswer.LayoutAnswer(binding.layoutAnswer2, binding.tvVariantNumber2, binding.tvVariantValue2)
        third = MarkAnswer.LayoutAnswer(binding.layoutAnswer3, binding.tvVariantNumber3, binding.tvVariantValue3)
        fourth = MarkAnswer.LayoutAnswer(binding.layoutAnswer4, binding.tvVariantNumber4, binding.tvVariantValue4)

        val learnWordDbHelper: LearnWordDbHelper = LearnWordDbHelper(this@MainActivity, "app", null, 1)




        learnWordDbHelper.AddWords()
        val count = learnWordDbHelper.getWordCount()
        println("$count !!!!!!!!!!!!!!!")

    // Рандомное слово и расположение его перевода в рандомном месте

        // Выбираем рандомный ID для элемента
        val randWordData = (1..10).random()

        // Получаем этот элемент
        val firstWord = learnWordDbHelper.getWordData(randWordData)
        // Получаем значение использовался ли он
        val isUsed = firstWord?.isUsed ?: Int
        // Если = 0, то не использовался
        if (isUsed.equals(0)) {
            val words = firstWord?.word
            val translate = firstWord?.translate
            binding.tvQuestionWord.text = words

            val randTranslate = (1..4).random()
            when (randTranslate) {
                1 -> {
                    binding.tvVariantValue1.text = translate
                    FirstVariantCorrect()
                }

                2 -> {
                    binding.tvVariantValue2.text = translate
                    SecondVariantCorrect()
                }

                3 -> {
                    binding.tvVariantValue3.text = translate
                    ThirdVariantCorrect()
                }

                4 -> {
                    binding.tvVariantValue4.text = translate
                    FourthVariantCorrect()
                }
            }
        }

        binding.buttonContinue.setOnClickListener {

            markAnswer.markAnswerNetral(first)

            markAnswer.markAnswerNetral(second)

            markAnswer.markAnswerNetral(third)

            markAnswer.markAnswerNetral(fourth)

            binding.resultLayout.isVisible = false
        }

    }


    fun FirstVariantCorrect() {

        binding.layoutAnswer1.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(first)
//            }
                showResultMessage(true)
            }
        }



        binding.layoutAnswer2.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(second)

                markAnswer.markAnswerCorrect(first)
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer3.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(third)

                markAnswer.markAnswerCorrect(first)
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer4.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(fourth)

                markAnswer.markAnswerCorrect(first)
//            }
                showResultMessage(false)
            }
        }
    }



    fun SecondVariantCorrect() {

        binding.layoutAnswer1.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(first )

                markAnswer.markAnswerCorrect(second)
//            }
                showResultMessage(false)
            }
        }



        binding.layoutAnswer2.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(second)
//            }
                showResultMessage(true)
            }
        }

        binding.layoutAnswer3.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(third)

                markAnswer.markAnswerCorrect(second)
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer4.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(fourth)

                markAnswer.markAnswerCorrect(second)
//            }
                showResultMessage(false)
            }
        }
    }



    fun ThirdVariantCorrect() {

        binding.layoutAnswer1.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(first)

                markAnswer.markAnswerCorrect(third)
//            }
                showResultMessage(false)
            }
        }



        binding.layoutAnswer2.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(second)

                markAnswer.markAnswerCorrect(third)
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer3.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(third )
//            }
                showResultMessage(true)
            }
        }

        binding.layoutAnswer4.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(fourth)

                markAnswer.markAnswerCorrect(third)
//            }
                showResultMessage(false)
            }
        }
    }



    fun FourthVariantCorrect() {

        binding.layoutAnswer1.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(first)

                markAnswer.markAnswerCorrect(fourth)
//            }
                showResultMessage(false)
            }
        }



        binding.layoutAnswer2.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(second)

                markAnswer.markAnswerCorrect(fourth)
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer3.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(third)

                markAnswer.markAnswerCorrect(fourth)
//            }
                showResultMessage(false)
            }
        }


        binding.layoutAnswer4.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(fourth)
//            }
                showResultMessage(true)
            }
        }
    }


    private fun showResultMessage(isCorrect: Boolean) {
        val color: Int
        val messageText: String
        val resultIconResource: Int
        if (isCorrect) {
            color = ContextCompat.getColor(this, R.color.green)
            resultIconResource = R.drawable.ic_correct
            messageText = "Correct!"
        } else {
            color = ContextCompat.getColor(this, R.color.red)
            resultIconResource = R.drawable.ic_wrong
            messageText = "Wrong!"
        }
        with(binding) {
            buttonSkip.isVisible = false
            resultLayout.isVisible = true
            buttonContinue.setTextColor(color)
            resultLayout.setBackgroundColor(color)
            tvResult.text = messageText
            ivResult.setImageResource(resultIconResource)
        }
    }
}


