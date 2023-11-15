package com.ale.examen_t4.Adapters.BD


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.comupnquispeaponfinal.Clases.Cartas
import com.example.comupnquispeaponfinal.Clases.Duelista
import com.example.comupnquispeaponfinal.Repositoris.CartaRepository
import com.example.comupnquispeaponfinal.Repositoris.DuelistaRepository


@Database(entities = [Duelista::class, Cartas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun duelistaRepository(): DuelistaRepository?
    abstract fun cartaRepository(): CartaRepository?

    companion object {
        fun getInstance(context: Context?): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "ExamenDB")
                .allowMainThreadQueries()
                .build()
        }
    }
}
