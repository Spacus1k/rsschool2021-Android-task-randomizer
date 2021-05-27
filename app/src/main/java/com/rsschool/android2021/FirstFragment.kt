package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.math.BigInteger

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)


        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val minValueEditText = view.findViewById<EditText>(R.id.min_value)
        val maxValueEditText = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            verificationEnteredData(
                minValueEditText.text.toString(),
                maxValueEditText.text.toString()
            )
        }
    }

    private fun verificationEnteredData(minValueText: String, maxValueText: String) {
        var errorMassage = verificationEnteredStringData(minValueText, maxValueText)

        if (errorMassage.isEmpty()) {
            errorMassage = verificationValuesEnteredData(minValueText, maxValueText)
        } else {
            activity?.let { showMassage(it, errorMassage, false) }
        }

        if (errorMassage.isEmpty()) {
            errorMassage = verificationValuesEnteredData(minValueText, maxValueText)
        } else {
            activity?.let { showMassage(it, errorMassage, false) }
        }
        if (errorMassage.isEmpty()) {
            (activity as MainActivity).onGeneratePressed(minValueText.toInt(), maxValueText.toInt())
        }
    }

    private fun verificationEnteredStringData(minValueText: String, maxValueText: String): String {
        return when {
            (minValueText.isEmpty() && maxValueText.isEmpty()) -> "Min and Max values are empty"
            minValueText.isEmpty() -> "Min value is empty"
            maxValueText.isEmpty() -> "Max value is empty"
            else -> ""
        }
    }

    private fun verificationValuesEnteredData(minValueText: String, maxValueText: String): String {
        val min: BigInteger = minValueText.toBigInteger()
        val max: BigInteger = maxValueText.toBigInteger()
        return when {
            min > Int.MAX_VALUE.toBigInteger() -> "Invalid data. Min value is too big"
            max > Int.MAX_VALUE.toBigInteger() -> "Invalid data. Max value is too big"
            min > max -> "Invalid data: Min > Max"
            minValueText == maxValueText -> "Invalid data: Min and Max are the same"
            else -> ""
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}