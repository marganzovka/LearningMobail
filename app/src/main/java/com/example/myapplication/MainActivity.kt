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

    // Cоздание объектов
    val markAnswer: MarkAnswer = MarkAnswer(this@MainActivity)
    val learnWordDbHelper: LearnWordDbHelper = LearnWordDbHelper(this@MainActivity, "app", null, 1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Создание объектов (сборка всех нужных View в один объект)
        var first = MarkAnswer.LayoutAnswer(binding.layoutAnswer1, binding.tvVariantNumber1, binding.tvVariantValue1)
        var second = MarkAnswer.LayoutAnswer(binding.layoutAnswer2, binding.tvVariantNumber2, binding.tvVariantValue2)
        var third = MarkAnswer.LayoutAnswer(binding.layoutAnswer3, binding.tvVariantNumber3, binding.tvVariantValue3)
        var fourth = MarkAnswer.LayoutAnswer(binding.layoutAnswer4, binding.tvVariantNumber4, binding.tvVariantValue4)

        //Добавление слов в бд
        learnWordDbHelper.AddWords()
        // Подсчет слов
        val count = learnWordDbHelper.getWordCount() //TODO
        println("$count !!!!!!!!!!!!!!!")

    // Рандомное слово и расположение его перевода в рандомном месте

        // Выбор рандомного ID для элемента
        val randWordData = (1..10).random()

        // Получение рандомного элемента
        val firstWord = learnWordDbHelper.getWordData(randWordData)
        // Получаение значение использовался ли он
        var isUsed = firstWord?.isUsed ?: Int
        // Если = 0, то не использовался
        if (isUsed.equals(0)) {
            // Получение слова и перевода из объекта
            val words = firstWord?.word
            val translate = firstWord?.translate
            // Вставка выбранного слова в title приложения
            binding.tvQuestionWord.text = words

            //Выбор рандомом места для перевода слова
            val randTranslate = (1..4).random()

            // В зависимости от того, какое место займет перевод
            when (randTranslate) {
                1 -> {
                    // Вставка перевода
                    binding.tvVariantValue1.text = translate
                    // Вызов метода отвечающего за визуальное отображение коректный ли дан ответ (принимает в первую очередь корректную ссылку(кнопку), после, все отсальные. "собранные" View
                    // передаются в том же порядке, что и ссылки(кнопки))
                    VariantCorrect(binding.layoutAnswer1, binding.layoutAnswer2, binding.layoutAnswer3, binding.layoutAnswer4, first, second, third, fourth)
                    //Изменения значения в бд "использовалось ли это слово ранее"
                    learnWordDbHelper.ChangeIsUsed(randTranslate)

                    // Метод выбирающий по еще одному рандомному слову
                    SetTextTranslate(binding.tvVariantValue2)
                    SetTextTranslate(binding.tvVariantValue3)
                    SetTextTranslate(binding.tvVariantValue4)

                }

                2 -> {
                    binding.tvVariantValue2.text = translate
                    VariantCorrect(binding.layoutAnswer2, binding.layoutAnswer1, binding.layoutAnswer3, binding.layoutAnswer4, second, first, third, fourth)
                    learnWordDbHelper.ChangeIsUsed(randTranslate)

                    SetTextTranslate(binding.tvVariantValue1)
                    SetTextTranslate(binding.tvVariantValue3)
                    SetTextTranslate(binding.tvVariantValue4)

                }

                3 -> {
                    binding.tvVariantValue3.text = translate
                    VariantCorrect(binding.layoutAnswer3, binding.layoutAnswer1, binding.layoutAnswer2, binding.layoutAnswer4, third, first, second, fourth)
                    learnWordDbHelper.ChangeIsUsed(randTranslate)

                    SetTextTranslate(binding.tvVariantValue1)
                    SetTextTranslate(binding.tvVariantValue2)
                    SetTextTranslate(binding.tvVariantValue4)

                }

                4 -> {
                    binding.tvVariantValue4.text = translate
                    VariantCorrect(binding.layoutAnswer4, binding.layoutAnswer1, binding.layoutAnswer1, binding.layoutAnswer3, fourth, first, second, third)
                    learnWordDbHelper.ChangeIsUsed(randTranslate)

                    SetTextTranslate(binding.tvVariantValue1)
                    SetTextTranslate(binding.tvVariantValue2)
                    SetTextTranslate(binding.tvVariantValue3)

                }
            }
        }


        // При нажатии на кнопку все возвращается в исходную позицию
        binding.buttonContinue.setOnClickListener {

            markAnswer.markAnswerNetral(first)
            markAnswer.markAnswerNetral(second)
            markAnswer.markAnswerNetral(third)
            markAnswer.markAnswerNetral(fourth)
            binding.resultLayout.isVisible = false
        }

    }

    fun SetTextTranslate(variant: TextView){
        do {
            val randTranslateThird = (1..4).random()
            val fourthWord = learnWordDbHelper.getWordData(randTranslateThird)
            var usedWord = true
            if (fourthWord?.isUsed?.equals(0)!!){
                val translate = fourthWord.translate
                variant.text = translate
                learnWordDbHelper.ChangeIsUsed(randTranslateThird)
                usedWord = false

            }
            else println("Копия")
        }while (usedWord)
    }

    fun VariantCorrect(layoutCorrect: LinearLayout, layoutFirstWord: LinearLayout, layoutSecondWord: LinearLayout, layoutThirdWord: LinearLayout,
                       first: MarkAnswer.LayoutAnswer, second: MarkAnswer.LayoutAnswer, third: MarkAnswer.LayoutAnswer, fourth: MarkAnswer.LayoutAnswer ) {

        layoutCorrect.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(first)
                showResultMessage(true)
            }
        }


        layoutFirstWord.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(second)
                markAnswer.markAnswerCorrect(first)
                showResultMessage(false)
            }
        }

        layoutSecondWord.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(third)
                markAnswer.markAnswerCorrect(first)
                showResultMessage(false)
            }
        }

        layoutThirdWord.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerWrong(fourth)
                markAnswer.markAnswerCorrect(first)
                showResultMessage(false)
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


