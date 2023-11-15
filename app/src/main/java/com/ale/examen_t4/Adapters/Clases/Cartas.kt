package com.ale.examen_t4.Adapters.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Cartas")
class Cartas {

    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "nombre")
    private var nombre: String? = null

    @ColumnInfo(name = "puntosdeataque")
    private var puntosdeataque: String? = null

    @ColumnInfo(name = "puntosdedefensa")
    private var puntosdedefensa: String? = null

    @ColumnInfo(name = "imagen")
    private var imagen: String? = null

    @ColumnInfo(name = "longitud")
    private var longitud: String? = null

    @ColumnInfo(name = "latitud")
    private var latitud: String? = null

    @ColumnInfo(name = "sincro")
    private var sincro: Boolean? = null

    @ColumnInfo(name = "idDuelista")
    private var idDuelista: String? = null

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }


    fun getNombre(): String? {
        return nombre
    }

    fun setNombre(nombre: String?) {
        this.nombre = nombre
    }

    fun getPuntosdeataque(): String? {
        return puntosdeataque
    }

    fun setPuntosdeataque(puntosdeataque: String?) {
        this.puntosdeataque = puntosdeataque
    }

    fun getPuntosdedefensa(): String? {
        return puntosdedefensa
    }

    fun setPuntosdedefensa(puntosdedefensa: String?) {
        this.puntosdedefensa = puntosdedefensa
    }

    fun getImagen(): String? {
        return imagen
    }

    fun setImagen(imagen: String?) {
        this.imagen = imagen
    }

    fun getLongitud(): String? {
        return longitud
    }

    fun setLongitud(longitud: String?) {
        this.longitud = longitud
    }

    fun getLatitud(): String? {
        return latitud
    }

    fun setLatitud(latitud: String?) {
        this.latitud = latitud
    }

    fun getSincro(): Boolean? {
        return sincro
    }

    fun setSincro(sincro: Boolean?) {
        this.sincro = sincro
    }

    fun getIdDuelista(): String? {
        return idDuelista
    }

    fun setIdDuelista(idDuelista: String?) {
        this.idDuelista = idDuelista
    }


}