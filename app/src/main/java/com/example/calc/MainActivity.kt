package com.example.calc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClear(view: View) {
        tvInput.setText("");
    }

    fun onOperator(view: View){
        if (tvInput.text != "" && !tvInput.text.last().equals('-') && !tvInput.text.last().equals('+') && !tvInput.text.last().equals('/') && !tvInput.text.last().equals('x')){
            var total = tvInput.text.count{tvInput.text.contains("-")} +
                    tvInput.text.count{tvInput.text.contains("+")} +
                    tvInput.text.count{tvInput.text.contains("/")} +
                    tvInput.text.count{tvInput.text.contains("x")}
            if(total <= 1 && ((view as Button).text) != "-"){
                tvInput.append((view as Button).text)
                lastNumeric = false
            }
            if(total <= 2 && ((view as Button).text) == "-"){
                tvInput.append((view as Button).text)
                lastNumeric = false
            }

        }

        if(tvInput.text == "" && ((view as Button).text) == "-"){
                tvInput.append("-")
            lastNumeric = false
        }
    }

    fun onPoint(view: View){
        if (tvInput.text != ""){
            var operatorSplit = tvInput.text.split('-','+','*','/')
            if (operatorSplit.size > 1){
                if(!operatorSplit[1].contains(".") && operatorSplit[1] != ""){
                    tvInput.append(".")
                }
            }else{
                if(!operatorSplit[0].contains(".")){
                    tvInput.append(".")
                }
            }
        }
    }

    fun onDigit(view: View){
        tvInput.append((view as Button).text);
        lastNumeric = true
    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view: View){
        if(lastNumeric) {
            var tvValue = tvInput.text.toString()
            try {
                if (tvValue.contains("-")) {

                    val splitValue = tvValue.split("-")

                    if(splitValue.size>2){
                        var one = splitValue[1]
                        var two = splitValue[2]
                        tvInput.text = tvValue +"\n"+(one.toDouble() - two.toDouble()).toString()
                    }else{
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]

                        tvInput.text = tvValue +"\n"+(one.toDouble() - two.toDouble()).toString()
                    }

                }

                if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    tvInput.text = tvValue +"\n"+(one.toDouble() + two.toDouble()).toString()
                }
                if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    tvInput.text = tvValue +"\n"+(one.toDouble() / two.toDouble()).toString()
                }

                if (tvValue.contains("x")) {
                    val splitValue = tvValue.split("x")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    tvInput.text = tvValue +"\n"+(one.toDouble() * two.toDouble()).toString()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
}