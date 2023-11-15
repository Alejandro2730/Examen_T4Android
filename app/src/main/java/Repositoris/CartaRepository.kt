package Repositoris

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.com.ale.examen_t4.Clases.Cartas


@Dao
interface CartaRepository {
    @get:Query("SELECT * FROM Cartas")
    val all: List<Any?>?

    @Insert
    fun create(cartas: Cartas?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movimientos: List<Cartas?>?)

    @get:Query("SELECT * FROM Cartas WHERE sincro = 0")
    val unsyncedCarta: List<Any?>?

    @get:Query("SELECT MAX(id) FROM Cartas")
    val lastId: Int

    @Update
    fun updateCarta(cartas: Cartas?)

    @Query("SELECT * FROM Cartas WHERE id = :cartaId")
    fun EncontrarCarta(cartaId: Int): Cartas?

    @Query("SELECT * FROM Cartas WHERE idDuelista = :idDuelista")
    fun getCartaDuelista(idDuelista: Int): List<Cartas?>?
}