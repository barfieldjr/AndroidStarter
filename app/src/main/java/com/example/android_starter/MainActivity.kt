package com.example.android_starter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {

    private lateinit var textGreeting: TextView
    private lateinit var editTextName: EditText
    private lateinit var spinnerGreetings: Spinner
    private lateinit var selectedGreeting: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textGreeting = findViewById(R.id.text_greeting)
        editTextName = findViewById(R.id.editText_name)
        spinnerGreetings = findViewById(R.id.spinner_greetings)
        val buttonGreet: Button = findViewById(R.id.button_greet)

        selectedGreeting = getString(R.string.hello)

        ArrayAdapter.createFromResource(
            this,
            R.array.greetings_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGreetings.adapter = adapter
        }

        spinnerGreetings.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedGreeting = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedGreeting = getString(R.string.hello)
            }
        }

        buttonGreet.setOnClickListener {
            val name = editTextName.text.toString()
            textGreeting.text = getString(R.string.greeting_template, selectedGreeting, name)
        }
    }
}
