package com.macode.ageinminutes

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_minutes.*
import java.text.SimpleDateFormat
import java.util.*


class MinutesFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_minutes, container, false)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.minutesToolbar)
        val selectDateButton = view.findViewById<Button>(R.id.minutesSelectDateButton)

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Age in Minutes"

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
            minutesSelectedDate.text = selectedDate
            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
            minutesInMinutesTillDate.text = differenceInMinutes.toString()
        }, year, month, day)
        dateSetListener.show()
        dateSetListener.datePicker.maxDate = Date().time - 86400000
        dateSetListener.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        dateSetListener.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
    }
}