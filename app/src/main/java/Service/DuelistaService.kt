package Service


import com..Clases.Duelista
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface DuelistaService {
    @GET("Duelista")
    fun getAllDuelista(
        @Query("name") name: String?,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Call<List<Duelista?>?>?

    @GET("Duelista/{id}")
    fun findDuelista(@Path("id") id: Int): Call<Duelista?>?

    @POST("Duelista")
    fun create(@Body duelista: Duelista?): Call<Duelista?>?

    @PUT("Duelista/{id}")
    fun update(@Path("id") id: Int, @Body duelista: Duelista?): Call<Duelista?>?

    @DELETE("Duelista/{id}")
    fun delete(@Path("id") id: Int): Call<Void?>?

    @POST("Duelista/upload")
    fun uploadDuelista(@Body duelistas: List<Duelista?>?): Call<Void?>?
}
