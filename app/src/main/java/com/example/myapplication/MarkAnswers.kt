package com.example.myapplication

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MarkAnswer(val context: Context) {

    class LayoutAnswer(val layout: LinearLayout, val tvVariantNumber: TextView, val tvVariantValue: TextView)
    fun markAnswerNetral(Layout: LayoutAnswer) {
        val layoutAnswer = Layout.layout
        val tvVariantNumber = Layout.tvVariantNumber
        val tvVariantValue = Layout.tvVariantValue


        layoutAnswer.background = ContextCompat.getDrawable(
            context,
            R.drawable.shape_raunded_container

        )


        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.textVariant
            )
        )


        tvVariantNumber.apply {
            background = ContextCompat.getDrawable(
                context,
                R.drawable.shape_rounded_variants
            )
            setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.textVariant
                )
            )

        }

    }


    fun markAnswerWrong(Layout: LayoutAnswer) {
        val layoutAnswer = Layout.layout
        val tvVariantNumber = Layout.tvVariantNumber
        val tvVariantValue = Layout.tvVariantValue

        layoutAnswer.background = ContextCompat.getDrawable(
            context, R.drawable.shape_raunded_container_wrong
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            context, R.drawable.shape_rounded_variants_wrong
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                context, R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                context, R.color.red
            )
        )


    }

    fun markAnswerCorrect(Layout: LayoutAnswer) {
        val layoutAnswer = Layout.layout
        val tvVariantNumber = Layout.tvVariantNumber
        val tvVariantValue = Layout.tvVariantValue

        layoutAnswer.background = ContextCompat.getDrawable(
            context, R.drawable.shape_raunded_container_correct
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            context, R.drawable.shape_rounded_variants_correct
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                context, R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                context, R.color.green
            )
        )
    }
}