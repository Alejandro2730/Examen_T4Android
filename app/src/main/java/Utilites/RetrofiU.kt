package Utilites

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofiU {
    fun build(): Retrofit {
        return Builder()
            .baseUrl("https://6477447c9233e82dd53b4dd6.mockapi.io/") // revisar
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
