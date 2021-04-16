package com.praveenkolay.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.praveenkolay.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateBtn.setOnClickListener{calculateTip()}

    }
    private fun calculateTip(){
        val stringTextInputField = binding.serviceCostEditText.text.toString()
        val cost = stringTextInputField.toDoubleOrNull()

        if(cost==null || cost==0.0){
            binding.tipResultText.text = " "
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.amazing_btn -> 0.20
            R.id.good_btn -> 0.10
            else -> 0.05
        }
        var tip = cost * tipPercentage
        val roundUp = binding.roundUpTip.isChecked
        if(roundUp){ tip = ceil(tip) }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResultText.text = getString(R.string.result, formattedTip)
    }
}