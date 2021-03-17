package com.macode.ageinminutes

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_minutes.*
import kotlinx.android.synthetic.main.fragment_weeks.*
import java.text.SimpleDateFormat
import java.util.*

class WeeksFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weeks, container, false)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.weeksToolbar)
        val selectDateButton = view.findViewById<Button>(R.id.weeksSelectDateButton)

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Age in Weeks"

        selectDateButton.setOnClickListener {view ->
            clickDatePicker(view)
        }

        return view
    }

    private fun clickDatePicker(view: View?) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dateSetListener = DatePickerDialog(requireActivity(), R.style.DialogDate, { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"
            weeksSelectedDate.text = selectedDate
            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInWeeks = theDate!!.time / 604800000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToWeeks = currentDate!!.time / 604800000
            val differenceInWeeks = currentDateToWeeks - selectedDateInWeeks
            weeksInWeeksTillDate.text = differenceInWeeks.toString()
        }, year, month, day)
        dateSetListener.show()
        dateSetListener.datePicker.maxDate = Date().time - 86400000
        dateSetListener.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        dateSetListener.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
    }
}