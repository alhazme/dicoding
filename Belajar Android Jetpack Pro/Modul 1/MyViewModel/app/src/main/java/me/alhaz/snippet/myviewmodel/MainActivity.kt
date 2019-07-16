package me.alhaz.snippet.myviewmodel

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText



class MainActivity : AppCompatActivity() {

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCalculate: Button
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModels()
        setupLayout()

        displayResult()
    }

    private fun setupViewModels() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun setupLayout() {
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate = findViewById(R.id.btn_calculate)
        btnCalculate.setOnClickListener {
            val width = edtWidth.text.toString()
            val height = edtHeight.text.toString()
            val length = edtLength.text.toString()
            if (validateForm()) {
                viewModel.calculate(width, height, length)
                displayResult()
            }
        }
    }

    private fun validateForm(): Boolean {

        val width = edtWidth.text.toString()
        val height = edtHeight.text.toString()
        val length = edtLength.text.toString()

        if (width.isEmpty()) {
            edtWidth.setError("Masih kosong")
            return false
        }
        if (height.isEmpty()) {
            edtHeight.setError("Masih kosong")
            return false
        }
        if (length.isEmpty()) {
            edtLength.setError("Masih kosong")
            return false
        }
        return true
    }

    private fun displayResult() {
        tvResult.setText(viewModel.result.toString())
    }
}
