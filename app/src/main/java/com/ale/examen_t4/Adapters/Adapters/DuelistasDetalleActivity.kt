package com.ale.examen_t4.Adapters.Adapters

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.comupnquispeaponfinal.BD.AppDatabase
import com.example.comupnquispeaponfinal.Clases.Duelista
import com.example.comupnquispeaponfinal.Repositoris.DuelistaRepository
import com.example.comupnquispeaponfinal.Utilies.RetrofiU
import retrofit2.Retrofit


class DuelistasDetallesActivity : AppCompatActivity() {
    var duelista: Duelista? = null
    var mRetrofit: Retrofit? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duelistas_detalles)
        mRetrofit = RetrofiU.build()
        val position = intent.getIntExtra("position", 0)
        val db: AppDatabase = AppDatabase.getInstance(this)
        val repository: DuelistaRepository = db.duelistaRepository()
        val duelista1: Duelista = repository.findDuelistaById(position)
        val tvNombre = findViewById<TextView>(R.id.tvnombredueslista)
        val bttnRegistrar = findViewById<Button>(R.id.dbtnRegistrar)
        val bttnVerC = findViewById<Button>(R.id.dbtnvercarta)
        tvNombre.setText(duelista1.getNombre())
        bttnRegistrar.setOnClickListener {
            val intent = Intent(this@DuelistasDetallesActivity, RegistroCartaActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            finish()
        }
        bttnVerC.setOnClickListener {
            val intent = Intent(this@DuelistasDetallesActivity, ListaCartaActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            finish()
        }
    }
}