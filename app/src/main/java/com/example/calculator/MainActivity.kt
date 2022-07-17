package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
        Toast.makeText(this,"Welcome to My Calculator",Toast.LENGTH_LONG).show()
    }
    fun onDigit(view : View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view:View){
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() - two.toDouble()

                    tvInput?.text = result.toString()
                }
                else if (tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() / two.toDouble()

                    tvInput?.text = result.toString()
                }
                else if (tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() + two.toDouble()

                    tvInput?.text = result.toString()
                }
                else if (tvValue.contains("x")) {
                    var splitValue = tvValue.split("x")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() * two.toDouble()

                    tvInput?.text = result.toString()
                }

            }
            catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }


    private fun isOperatorAdded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")||value.contains("x")||value.contains("+")||value.contains("-")
        }
    }
}



