package Repositoris


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.com.ale.examen_t4.Clases.Duelista


@Dao
interface DuelistaRepository {
    @get:Query("SELECT * FROM Duelistas")
    val all: List<Any?>?

    @Insert
    fun create(duelista: Duelista?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(duelistas: List<Duelista?>?)

    @get:Query("SELECT * FROM Duelistas WHERE sincro = 0")
    val unSincro: List<Any?>?

    @Update
    fun updateDuelista(duelista: Duelista?)

    @get:Query("SELECT MAX(id) FROM Duelistas")
    val lastId: Int

    @Query("SELECT * FROM Duelistas WHERE id = :Id")
    fun findDuelistaById(Id: Int): Duelista?
}
