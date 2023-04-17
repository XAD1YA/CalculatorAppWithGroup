package org.diyorbek.calculatorappwithgroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)

        val one: MaterialButton = findViewById(R.id.btnOne)
        val two: MaterialButton = findViewById(R.id.btnTwo)
        val three: MaterialButton = findViewById(R.id.btnThree)
        val four: MaterialButton = findViewById(R.id.btnFour)
        val five: MaterialButton = findViewById(R.id.btnFive)
        val six: MaterialButton = findViewById(R.id.btnSix)
        val seven: MaterialButton = findViewById(R.id.btnSeven)
        val eight: MaterialButton = findViewById(R.id.btnEight)
        val nine: MaterialButton = findViewById(R.id.btnNine)
        val zero: MaterialButton = findViewById(R.id.btnZero)
        val tvOpen: MaterialButton = findViewById(R.id.tvOpen)
        val tvClose: MaterialButton = findViewById(R.id.tvClose)
        val dot: MaterialButton = findViewById(R.id.btnComa)
        val add: MaterialButton = findViewById(R.id.btnPlus)
        val mul: MaterialButton = findViewById(R.id.btnMultiplication)
        val min: MaterialButton = findViewById(R.id.btnMinus)
        val div: MaterialButton = findViewById(R.id.tvDivide)
        val equal: MaterialButton = findViewById(R.id.btnEquals)
        val back: MaterialButton = findViewById(R.id.btnTringle)
        val clear: MaterialButton = findViewById(R.id.tvClear)

        one.setOnClickListener { addExpression("1", true) }
        two.setOnClickListener { addExpression("2", true) }
        three.setOnClickListener { addExpression("3", true) }
        four.setOnClickListener { addExpression("4", true) }
        five.setOnClickListener { addExpression("5", true) }
        six.setOnClickListener { addExpression("6", true) }
        seven.setOnClickListener { addExpression("7", true) }
        nine.setOnClickListener { addExpression("8", true) }
        eight.setOnClickListener { addExpression("9", true) }
        zero.setOnClickListener { addExpression("0", true) }

        dot.setOnClickListener { addExpression(".", true) }

        add.setOnClickListener { addExpression("+", false) }
        mul.setOnClickListener { addExpression("*", false) }
        min.setOnClickListener { addExpression("-", false) }
        div.setOnClickListener { addExpression("/", false) }
        tvOpen.setOnClickListener { addExpression("(", false) }
        tvClose.setOnClickListener { addExpression(")", false) }
        clear.setOnClickListener {
            tvResult.text = ""
            tvExpression.text = ""
        }
        equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString().trim()).build()
                tvResult.text = expression.evaluate().toString()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        back.setOnClickListener {
            val text = tvExpression.text.toString()
            if (text.isNotEmpty()) {
                tvExpression.text = text.substring(0, text.length - 1)
            }
            tvResult.text = ""
        }


    }

    private fun addExpression(string: String, isBtn: Boolean) {
        if (tvResult.text.isNotBlank()) {
            tvExpression.text = ""
        }
        if (isBtn) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text.toString())
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}