package com.example.bycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AppViewModel
    private val historyAdapter = HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AppViewModel::class.java]

        init()

        binding.rvHistory.adapter = historyAdapter
        binding.rvHistory.layoutManager = LinearLayoutManager(this)

        viewModel.operation.observe(this, androidx.lifecycle.Observer {
            binding.operationTv.text = it
        })

        viewModel.result.observe(this, androidx.lifecycle.Observer {
            binding.resultTv.text = it
        })

    }


    private fun init() {

        setNumber()
        setOperators()
        setClean()
        setExpectedResult()
    }

    private fun setExpectedResult() {
        (binding.equalButton).setOnClickListener {
            try {
                val expectedExpression = ExpressionBuilder(binding.operationTv.text.toString()).build()
                viewModel.setOperation(binding.operationTv.text.toString())
                val expectedResult = expectedExpression.evaluate()
                val longResult = expectedResult.toLong()
                if (expectedResult == longResult.toDouble()) {
                    binding.resultTv.text = longResult.toString()
                    viewModel.setResult(longResult.toString())
                } else {
                    binding.resultTv.text = expectedResult.toString()
                    viewModel.setResult(expectedResult.toString())
                }

            } catch (e: Exception) {
            }
        }
    }

    private fun setClean() {
        binding.clearButton.setOnClickListener {
            binding.operationTv.text = ""
            binding.resultTv.text = ""
        }
    }

    private fun setOperators() {
        binding.additionButton.setOnClickListener { calculateTheExpress("+", false) }
        binding.subtractionButton.setOnClickListener { calculateTheExpress("-", false) }
        binding.multiplicationButton.setOnClickListener { calculateTheExpress("*", false) }
        binding.divisionButton.setOnClickListener { calculateTheExpress("/", false) }
    }

    private fun setNumber() {
        binding.zeroButton.setOnClickListener { calculateTheExpress("0", true) }
        binding.oneButton.setOnClickListener { calculateTheExpress("1", true) }
        binding.twoButton.setOnClickListener { calculateTheExpress("2", true) }
        binding.threeButton.setOnClickListener { calculateTheExpress("3", true) }
        binding.fourButton.setOnClickListener { calculateTheExpress("4", true) }
        binding.fiveButton.setOnClickListener { calculateTheExpress("5", true) }
        binding.sixButton.setOnClickListener { calculateTheExpress("6", true) }
        binding.sevenButton.setOnClickListener { calculateTheExpress("7", true) }
        binding.eightButton.setOnClickListener { calculateTheExpress("8", true) }
        binding.nineButton.setOnClickListener { calculateTheExpress("9", true) }
        binding.dotButton.setOnClickListener { calculateTheExpress(".", true) }
    }

    private fun calculateTheExpress(data: String, clearData: Boolean) {
        if (binding.resultTv.text.isNotEmpty()) {
            binding.operationTv.text = ""
        }
        if (clearData) {
            binding.resultTv.text = ""
            binding.operationTv.append(data)
        } else {
            binding.operationTv.append(binding.resultTv.text)
            binding.operationTv.append(data)
            binding.resultTv.text = ""
        }
    }

}