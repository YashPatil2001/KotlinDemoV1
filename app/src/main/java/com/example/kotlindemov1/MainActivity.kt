package com.example.kotlindemov1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemov1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        val repository = QuotesRepository(quotesApi)

        val viewModel = ViewModelProvider(this,QuoteViewModelFactory(repository)).get(QuoteViewModel::class.java)
        viewModel.quotes.observe(this) { it ->

            when(it){
                is Response.Loader -> {}
                is Response.Success -> {
                    Log.e(TAG, "onCreate: quoteList >>>> ${it.data}" )
                    it.data?.let {
                        Toast.makeText(this,it.results.size.toString(),Toast.LENGTH_SHORT).show()
                    }

                }
                is Response.Error -> {
                    Log.e(TAG, "onCreate: Error >>>> ${it.data}" )
                   Toast.makeText(this,"Error ${it.errorMessage}",Toast.LENGTH_SHORT).show()
                   Toast.makeText(this,"Error ${it.errorCode}",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


    fun getTotal(a: Int,b: Int): Int{
        return a + b;
    }
}