package com.example.quots

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
    get()=findViewById(R.id.quoteText)

    private val author: TextView
    get()=findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuots())
    }

     fun setQuote(quote:Quote){
         quoteText.text=quote.text
         author.text = quote.author
     }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.prevQuots())
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuots())
    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuots().text)
        startActivity(intent)
    }
}