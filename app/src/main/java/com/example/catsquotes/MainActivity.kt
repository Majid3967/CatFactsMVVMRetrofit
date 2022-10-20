package com.example.catsquotes

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.catsquotes.api.QuoteService
import com.example.catsquotes.api.RetrofitHelper
import com.example.catsquotes.repository.QuoteRepsoitory
import com.example.catsquotes.viewmodels.MainViewModel
import com.example.catsquotes.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text: TextView = findViewById(R.id.text) as TextView
        val button: Button = findViewById(R.id.button) as Button

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val repository = QuoteRepsoitory(quoteService)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quote.observe(this, Observer {
            text.text = it.fact
        })

        button.setOnClickListener {
            mainViewModel.refresh()
            mainViewModel.quote.observe(this, Observer {
                text.text = it.fact
            })
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        val builder = AlertDialog.Builder(this, R.style.Theme_CatsQuotes)

        builder.setTitle("Exit")

        builder.setMessage("Are you sure?")

        builder.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener { dialogInterface, i -> finish() })

        builder.setNegativeButton(
            "No",
            DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(
                    this,
                    "Exit Cancel",
                    Toast.LENGTH_LONG
                ).show()
            })

        builder.setNeutralButton(
            "Cancel",
            DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(
                    this,
                    "Clicked Cancel",
                    Toast.LENGTH_LONG
                ).show()
            })

        val dialog: AlertDialog = builder.create()

        dialog.show()


    }

}