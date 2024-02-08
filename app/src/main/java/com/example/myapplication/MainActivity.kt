package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Cоздание объектов
    val markAnswer: MarkAnswer = MarkAnswer(this@MainActivity)
    val learnWordDbHelper: LearnWordDbHelper = LearnWordDbHelper(this@MainActivity, "app", null, 1)
    lateinit var first : MarkAnswer.LayoutAnswer
    lateinit var second : MarkAnswer.LayoutAnswer
    lateinit var third : MarkAnswer.LayoutAnswer
    lateinit var fourth : MarkAnswer.LayoutAnswer
    var id: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Создание объектов (сборка всех нужных View в один объект)
        first = MarkAnswer.LayoutAnswer(binding.layoutAnswer1, binding.tvVariantNumber1, binding.tvVariantValue1)
        second = MarkAnswer.LayoutAnswer(binding.layoutAnswer2, binding.tvVariantNumber2, binding.tvVariantValue2)
        third = MarkAnswer.LayoutAnswer(binding.layoutAnswer3, binding.tvVariantNumber3, binding.tvVariantValue3)
        fourth = MarkAnswer.LayoutAnswer(binding.layoutAnswer4, binding.tvVariantNumber4, binding.tvVariantValue4)

        var count: Int
        count = learnWordDbHelper.getWordCount()
        //Добавление слов в бд
        if (count == 0){
        learnWordDbHelper.AddWords()}


    // Рандомное слово и расположение его перевода в рандомном месте
        RandomWord()




        // При нажатии на кнопку все возвращается в исходную позицию
        binding.buttonContinue.setOnClickListener {
            Reset()
            binding.buttonSkip.isVisible = true
            RandomWord()

        }

        binding.buttonStart.setOnClickListener{
            binding.tvStart.isVisible = false
            binding.buttonStart.isVisible = false

            binding.ibClose.isVisible = true
            binding.tvQuestionWord.isVisible = true
            binding.layoutWithVariant.isVisible = true
            binding.buttonSkip.isVisible = true

        }

        binding.ibClose.setOnClickListener {
            binding.tvStart.isVisible = true
            binding.buttonStart.isVisible = true

            binding.ibClose.isVisible = false
            binding.tvQuestionWord.isVisible = false
            binding.layoutWithVariant.isVisible = false
            binding.buttonSkip.isVisible = false
            Reset()

        }
        binding.buttonSkip.setOnClickListener {
            Reset()
            RandomWord()
        }

        binding.buttonRestart.setOnClickListener {
            learnWordDbHelper.RestartIsUsed()
            binding.tvStart.isVisible = false
            binding.buttonStart.isVisible = false

            binding.buttonRestart.isVisible = false
            binding.ibClose.isVisible = true
            binding.tvQuestionWord.isVisible = true
            binding.layoutWithVariant.isVisible = true
            binding.buttonSkip.isVisible = true
        }

    }

    fun RandomWord(){
        var countNotLearned = learnWordDbHelper.getWordNotLearnedCount()
        println(countNotLearned)
        if (countNotLearned > 0){
        // Если = 0, то не использовался
        do  {
            // Выбор рандомного ID для элемента
            val randWordData = (1..30).random()

            // Получение рандомного элемента
            val firstWord = learnWordDbHelper.getWordData(randWordData)
            // Получаение значение использовался ли он
            var isUsed = firstWord?.isUsed ?: Int

            // Получение слова и перевода из объекта
            val words = firstWord?.word
            val translate = firstWord?.translate
            id = firstWord?.id
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


                    // Метод выбирающий по еще одному рандомному слову
                    var translateFirst: String
                    var translateSecond : String
                    var translateThird : String
                    // Циклы не дающие взять одинаковые слова
                    do {
                        println("Первое слово")
                        translateFirst = SetTextTranslate(binding.tvVariantValue2)
                    }while (translateFirst == translate)

                    do {
                        translateSecond = SetTextTranslate(binding.tvVariantValue3)
                        println("Второе слово")
                    }while (translateSecond == translateFirst || translateSecond == translate)

                    do {
                        println("Третье слово")
                        translateThird = SetTextTranslate(binding.tvVariantValue4)
                    }while (translateSecond == translateThird || translateFirst == translateThird || translateThird == translate )

                }

                2 -> {
                    binding.tvVariantValue2.text = translate
                    VariantCorrect(binding.layoutAnswer2, binding.layoutAnswer1, binding.layoutAnswer3, binding.layoutAnswer4, second, first, third, fourth)
                    var translateFirst: String
                    var translateSecond : String
                    var translateThird : String

                    do {
                        println("Первое слово")
                        translateFirst = SetTextTranslate(binding.tvVariantValue1)
                    }while (translateFirst == translate)
                    do {
                        println("Второе слово")

                        translateSecond = SetTextTranslate(binding.tvVariantValue3)
                    }while (translateSecond == translateFirst || translateSecond == translate)
                    do {
                        println("Третье слово")

                        translateThird = SetTextTranslate(binding.tvVariantValue4)
                    }while (translateSecond == translateThird || translateFirst == translateThird || translateThird == translate )


                }

                3 -> {
                    binding.tvVariantValue3.text = translate
                    VariantCorrect(binding.layoutAnswer3, binding.layoutAnswer1, binding.layoutAnswer2, binding.layoutAnswer4, third, first, second, fourth)

                    var translateFirst : String
                    var translateSecond : String
                    var translateThird : String

                    do {
                        println("Первое слово")
                        translateFirst = SetTextTranslate(binding.tvVariantValue1)
                    }while (translateFirst == translate)

                    do {
                        println("Второе слово")

                        translateSecond = SetTextTranslate(binding.tvVariantValue2)
                    }while (translateSecond == translateFirst || translateSecond == translate)

                    do {
                        println("Третье слово")

                        translateThird = SetTextTranslate(binding.tvVariantValue4)
                    }while (translateThird == translateFirst || translateThird == translateSecond || translateThird == translate )


                }

                4 -> {
                    binding.tvVariantValue4.text = translate
                    VariantCorrect(binding.layoutAnswer4, binding.layoutAnswer1, binding.layoutAnswer2, binding.layoutAnswer3, fourth, first, second, third)

                    var translateFirst : String
                    var translateSecond : String
                    var translateThird : String

                    do {
                        println("Первое слово")
                        translateFirst = SetTextTranslate(binding.tvVariantValue1)
                    }while (translateFirst == translate)

                    do {
                        println("Второе слово")

                        translateSecond = SetTextTranslate(binding.tvVariantValue2)
                    }while (translateSecond == translateFirst || translateSecond == translate)

                    do {
                        println("Третье слово")

                        translateThird = SetTextTranslate(binding.tvVariantValue3)
                    }while (translateSecond == translateThird || translateFirst == translateThird || translateThird == translate )


                }
            }
        }while (isUsed.equals(1))}
        else Finish()
    }

    fun Finish(){
        binding.tvStart.text = "You learned all the words!"
        binding.tvStart.isVisible = true

        binding.buttonRestart.isVisible = true
        binding.buttonStart.isVisible = false
        binding.ibClose.isVisible = false
        binding.tvQuestionWord.isVisible = false
        binding.layoutWithVariant.isVisible = false
        binding.buttonSkip.isVisible = false
        Reset()
    }

    // Метод сбрасывающий визуал в исходное состояние
    fun Reset(){
        markAnswer.markAnswerNetral(first)
        markAnswer.markAnswerNetral(second)
        markAnswer.markAnswerNetral(third)
        markAnswer.markAnswerNetral(fourth)
        binding.resultLayout.isVisible = false
    }

    // Метод выбирающий рандомное слово с бд, и проверяющий, использовалось ли оно ранее и возращающий его перевод
    fun SetTextTranslate(variant: TextView): String{
        var translate: String
        do {
            val randTranslateThird = (1..30).random()
            val fourthWord = learnWordDbHelper.getWordData(randTranslateThird)
            var usedWord = true
            var countUsedWord = learnWordDbHelper.getWordNotLearnedCount()
            println(countUsedWord)
            if (fourthWord?.isUsed?.equals(0)!!){
                translate = fourthWord.translate
                variant.text = translate
                usedWord = false
                println(translate)
                return translate
            }
            else if (countUsedWord <= 3){
                translate = fourthWord.translate
                variant.text = translate
                usedWord = false
                println(translate)
                return translate

            }else translate = "Copy"

        }while (usedWord)

        return translate

    }

    // Метод отображающий визуальную часть при выборе ответа
    fun VariantCorrect(layoutCorrect: LinearLayout, layoutFirstWord: LinearLayout, layoutSecondWord: LinearLayout, layoutThirdWord: LinearLayout,
                       first: MarkAnswer.LayoutAnswer, second: MarkAnswer.LayoutAnswer, third: MarkAnswer.LayoutAnswer, fourth: MarkAnswer.LayoutAnswer ) {

        layoutCorrect.setOnClickListener {
            if (binding.resultLayout.isVisible) {
                Toast.makeText(this, "reply accepted", Toast.LENGTH_SHORT).show()
            } else {
                markAnswer.markAnswerCorrect(first)
                showResultMessage(true)
                // Если ответ верный, то слово считается выученным и помечается в БД
                learnWordDbHelper.ChangeIsUsed(id)
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



// Метод отображающий результат в зависимости от того, правильный ли вариант выбрал пользователь
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


