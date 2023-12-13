package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val markAnswer: MarkAnswer = MarkAnswer(this@MainActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val learnWordDbHelper: LearnWordDbHelper =
            LearnWordDbHelper(this@MainActivity, "app", null, 1)




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

            markAnswer.markAnswerNetral(
                binding.layoutAnswer1,
                binding.tvVariantNumber1,
                binding.tvVariantValue1
            )

            markAnswer.markAnswerNetral(
                binding.layoutAnswer2,
                binding.tvVariantNumber2,
                binding.tvVariantValue2
            )

            markAnswer.markAnswerNetral(
                binding.layoutAnswer3,
                binding.tvVariantNumber3,
                binding.tvVariantValue3
            )

            markAnswer.markAnswerNetral(
                binding.layoutAnswer4,
                binding.tvVariantNumber4,
                binding.tvVariantValue4
            )

            binding.resultLayout.isVisible = false
        }
    }

    // TODO 1
    fun FirstVariantCorrect() {

        binding.layoutAnswer1.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )
//            }
                showResultMessage(true)
            }
        }



        binding.layoutAnswer2.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber2,
                    binding.tvVariantValue2
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer3.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber3,
                    binding.tvVariantValue3
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer4.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer4,
                    binding.tvVariantNumber4,
                    binding.tvVariantValue4
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )
//            }
                showResultMessage(false)
            }
        }
    }


    // TODO 2
    fun SecondVariantCorrect() {

        binding.layoutAnswer1.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer2,
                    binding.tvVariantNumber2,
                    binding.tvVariantValue2
                )
//            }
                showResultMessage(false)
            }
        }



        binding.layoutAnswer2.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer2,
                    binding.tvVariantNumber2,
                    binding.tvVariantValue2
                )
//            }
                showResultMessage(true)
            }
        }

        binding.layoutAnswer3.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber3,
                    binding.tvVariantValue3
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer2,
                    binding.tvVariantNumber2,
                    binding.tvVariantValue2
                )
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer4.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer4,
                    binding.tvVariantNumber4,
                    binding.tvVariantValue4
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer2,
                    binding.tvVariantNumber2,
                    binding.tvVariantValue2
                )
//            }
                showResultMessage(false)
            }
        }
    }


    // TODO 3
    fun ThirdVariantCorrect() {

        binding.layoutAnswer1.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer3,
                    binding.tvVariantNumber3,
                    binding.tvVariantValue3
                )
//            }
                showResultMessage(false)
            }
        }



        binding.layoutAnswer2.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer2,
                    binding.tvVariantNumber2,
                    binding.tvVariantValue2
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer3,
                    binding.tvVariantNumber3,
                    binding.tvVariantValue3
                )
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer3.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer3,
                    binding.tvVariantNumber3,
                    binding.tvVariantValue3
                )
//            }
                showResultMessage(true)
            }
        }

        binding.layoutAnswer4.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer4,
                    binding.tvVariantNumber4,
                    binding.tvVariantValue4
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer3,
                    binding.tvVariantNumber3,
                    binding.tvVariantValue3
                )
//            }
                showResultMessage(false)
            }
        }
    }


    // TODO 4
    fun FourthVariantCorrect() {

        binding.layoutAnswer1.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )
//            }
                showResultMessage(false)
            }
        }



        binding.layoutAnswer2.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer2,
                    binding.tvVariantNumber2,
                    binding.tvVariantValue2
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer4,
                    binding.tvVariantNumber4,
                    binding.tvVariantValue4
                )
//            }
                showResultMessage(false)
            }
        }

        binding.layoutAnswer3.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(
                    binding.layoutAnswer2,
                    binding.tvVariantNumber3,
                    binding.tvVariantValue3
                )

                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer4,
                    binding.tvVariantNumber4,
                    binding.tvVariantValue4
                )
//            }
                showResultMessage(false)
            }
        }


        binding.layoutAnswer4.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(
                    binding.layoutAnswer4,
                    binding.tvVariantNumber4,
                    binding.tvVariantValue4
                )
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


