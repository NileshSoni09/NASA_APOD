package com.example.nasa

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nasa.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // view model instance
        var viewModel: MainActivityViewModel =
            ViewModelProvider(this)[MainActivityViewModel::class.java]

        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        Log.d("Main Activity Observer", "currentDate:" + currentDate)
        txtDate?.text = currentDate

        viewModel.getAPODData(txtDate?.text.toString())

        val onDateSetListener =
            OnDateSetListener { view, year, month, day ->
                Log.v("", "selected date:: $year, $month, $day")
                val monthNum = month + 1
                txtDate?.text = "$year-$monthNum-$day"
            }

        datePicker?.setOnClickListener {
            val dateMonthYear = txtDate.text.toString().split("-")
            if(dateMonthYear.size > 2) {
                val dialog = DatePickerDialog(this, onDateSetListener, dateMonthYear[0].toInt(),
                    dateMonthYear[1].toInt() - 1, dateMonthYear[2].toInt())
                dialog.show()
            }
        }

        btnGetDetails?.setOnClickListener {
            viewModel.getAPODData(txtDate?.text.toString())
        }

        viewModel.apodDataModelUpdate.observe(this, Observer {
            Log.d("Main Activity Observer", "data:" + it)
            txtTitle?.text = it.title
            txtDescription?.text = it.explanation

            it.url?.let{
                Glide.with(this)
                    .load(it)
                    .into(image)
            }
        })

    }
}