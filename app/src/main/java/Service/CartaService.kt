package Service


import com.example.ale.examen_t4.Clases.Cartas
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface CartaService {
    @GET("Cartas")
    fun getAllCartas(
        @Query("name") name: String?,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Call<List<Cartas?>?>?

    @GET("Cartas/{id}")
    fun findCartas(@Path("id") id: Int): Call<Cartas?>?

    @POST("Cartas")
    fun create(@Body cartas: Cartas?): Call<Cartas?>?

    @PUT("Cartas/{id}")
    fun update(@Path("id") id: Int, @Body cartas: Cartas?): Call<Cartas?>?

    @DELETE("Cartas/{id}")
    fun delete(@Path("id") id: Int): Call<Void?>?

    @GET("Cartas/{id}")
    fun getCartasByDuelistaId(
        @Query("name") name: String?,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Path("id") id: Int
    ): List<List<Cartas?>?>?

    @POST("Cartas/upload")
    fun uploadCartas(@Body cartas: List<Cartas?>?): Call<Void?>?

    @POST("image")
    fun subirImagen(@Body imagen: ImageToSave?): Call<ImageResponse?>?
    class ImageResponse {
        @com.google.gson.annotations.SerializedName("url")
        val url: String? = null
    }

    class ImageToSave(var base64Image: String)
}
