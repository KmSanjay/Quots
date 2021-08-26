package com.example.quots

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context : Context) :ViewModel() {
    private var quoteList:Array<Quote> = emptyArray()
    private  var index=0

    init {
        quoteList= loadQuoteFromAsset()
    }

    private fun loadQuoteFromAsset(): Array<Quote> {
      val inputStream = context.assets.open("quots.json")
        val size: Int =inputStream.available()
        val buffer =ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json =String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return  gson.fromJson(json,Array<Quote>::class.java)

    }

    fun getQuots() =quoteList[index]
    fun nextQuots() = quoteList[++index]
    fun prevQuots() = if (index==0) quoteList[index] else
        quoteList[--index]
}