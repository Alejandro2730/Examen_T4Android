package com.ale.examen_t4.Adapters.Adapters


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comupnquispeaponfinal.BD.AppDatabase
import com.example.comupnquispeaponfinal.Clases.Cartas
import com.example.comupnquispeaponfinal.Clases.Duelista
import com.example.comupnquispeaponfinal.Repositoris.CartaRepository
import com.example.comupnquispeaponfinal.Repositoris.DuelistaRepository
import com.example.comupnquispeaponfinal.Service.CartaService
import com.example.comupnquispeaponfinal.Service.DuelistaService
import com.example.comupnquispeaponfinal.Utilies.RetrofiU
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class ListaCartaActivity : AppCompatActivity() {
    var mRvLista: RecyclerView? = null
    var mIsLoading = false
    var mPage = 1
    var cdata: MutableList<Cartas?> = ArrayList<Cartas?>()
    var ddata: List<Duelista> = ArrayList<Duelista>()
    var mAdapter = CartasAdapter(cdata, this)
    var cAdapter = DuelistaAdapter(ddata, this)
    var RetrofitC: Retrofit? = null
    var context: Context = this
    var currentFilter = ""
    var idDuel = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_carta)
        idDuel = intent.getIntExtra("position", 0) //RECIVI EL POKEMON EXACTO
        RetrofitC = RetrofiU.build()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mRvLista = findViewById<RecyclerView>(R.id.rvListaCartas)
        mRvLista.setLayoutManager(layoutManager)
        mRvLista.setAdapter(mAdapter)
        val btnVolver = findViewById<Button>(R.id.VolverC)
        val db: AppDatabase = AppDatabase.getInstance(context)
        val repository: CartaRepository = db.cartaRepository()
        val cartas: List<Cartas> = repository.getAll() //mandamos la lista de los pokemones
        Log.i("MAIN_APP: DB", com.google.gson.Gson().toJson(cartas))
        Log.i("MAIN_APP", idDuel.toString() + "")
        mAdapter.setCartas(cartas)
        mAdapter.notifyDataSetChanged()
        btnVolver.setOnClickListener {
            val intent = Intent(this@ListaCartaActivity, ListaDuelistaActivity::class.java)
            startActivity(intent)
            finish()
        }
        mRvLista.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!mIsLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == cdata.size - 1) {
                        mPage++
                        loadMore(mPage)
                    }
                }
            }
        })
        mRvLista.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!mIsLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == cdata.size - 1) {
                        mPage++
                        loadMore(mPage)
                    }
                }
            }
        })
    }

    private fun uploadToWebService(nextPage: Int) {
        val db: AppDatabase = AppDatabase.getInstance(context)
        db.clearAllTables()
        val service: CartaService = RetrofitC.create(CartaService::class.java)
        service.getAllCartas(currentFilter, 50, nextPage)
            .enqueue(object : Callback<List<Cartas?>?>() {
                fun onResponse(call: Call<List<Cartas?>?>?, response: Response<List<Cartas?>?>) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Inserta los datos en la base de datos
                        val db: AppDatabase = AppDatabase.getInstance(this@ListaCartaActivity)
                        val repository: CartaRepository = db.cartaRepository()
                        repository.insertAll(response.body())

                        // Actualiza los datos en el adaptador y notifica los cambios
                        val newData: List<Cartas> = repository.getAll()
                        mAdapter.setCartas(newData)
                        mAdapter.notifyDataSetChanged()
                    }
                }

                fun onFailure(call: Call<List<Cartas?>?>?, t: Throwable?) {
                    // Maneja el error de la llamada al servicio MockAPI
                }
            })
        val service2: DuelistaService = RetrofitC.create(DuelistaService::class.java)
        service2.getAllDuelista(currentFilter, 50, nextPage)
            .enqueue(object : Callback<List<Duelista?>?>() {
                fun onResponse(
                    call: Call<List<Duelista?>?>?,
                    response: Response<List<Duelista?>?>
                ) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Inserta los datos en la base de datos
                        val db: AppDatabase = AppDatabase.getInstance(this@ListaCartaActivity)
                        val repository: DuelistaRepository = db.duelistaRepository()
                        repository.insertAll(response.body())

                        // Actualiza los datos en el adaptador y notifica los cambios
                        val newData: List<Duelista> = repository.getAll()
                        cAdapter.setDuelista(newData)
                        cAdapter.notifyDataSetChanged()
                    }
                }

                fun onFailure(call: Call<List<Duelista?>?>?, t: Throwable?) {
                    // Maneja el error de la llamada al servicio MockAPI
                }
            })
    }

    private val isNetworkConnected: Boolean
        private get() {
            val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }

    private fun loadMore(nextPage: Int) {
        mIsLoading = true
        cdata.add(null)
        mAdapter.notifyItemInserted(cdata.size - 1)
        val service: CartaService = RetrofitC.create(CartaService::class.java)
        Log.i("MAIN_APP  Page:", nextPage.toString())
        service.getAllCartas(currentFilter, 100, nextPage)
            .enqueue(object : Callback<List<Cartas?>?>() {
                // Cambia el número de registros por página según tus necesidades
                fun onResponse(call: Call<List<Cartas?>?>?, response: Response<List<Cartas?>?>) {
                    if (response.isSuccessful() && response.body() != null) {
                        cdata.removeAt(cdata.size - 1)
                        mAdapter.notifyItemRemoved(cdata.size - 1)
                        cdata.addAll(response.body())
                        mAdapter.notifyDataSetChanged()
                        mIsLoading = false

                        // Si hay más registros disponibles, cargar la siguiente página
                        if (response.body()
                                .size() >= 100
                        ) { // Cambia el número de registros por página según tus necesidades
                            loadMore(nextPage + 1)
                        }
                    }
                }

                fun onFailure(call: Call<List<Cartas?>?>?, t: Throwable?) {
                    // Manejar error de la llamada al servicio MockAPI
                }
            })
    }
}