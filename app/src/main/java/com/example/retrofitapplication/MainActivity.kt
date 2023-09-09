package com.example.retrofitapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class MainActivity : AppCompatActivity() {
   companion object{
       const val BASE_URL = "https://starnoh.pythonanywhere.com/api/"
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postMyData()
    }

    private fun postMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val apiService = retrofitBuilder.create(ApiCall::class.java)
        val body = MyBody("Jane","jane0Mary242")


        lifecycleScope.launch {


           val result =  apiService.postData(body)
            try {
                if (result.isSuccessful) {
                    val response = result.body()
                    if (response != null) {
                        val data = response.message
                        Toast.makeText(applicationContext, data.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                else{
                    Toast.makeText(applicationContext, "Error  : ${result.code()} - ${result.message()}", Toast.LENGTH_SHORT).show()
                    }// end else

                }catch (e:IOException){
                Toast.makeText(applicationContext, "Network error : ${e.message}", Toast.LENGTH_SHORT).show()
                }

        }



    }}


