package com.ale.examen_t4.Adapters.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Duelistas")
class Duelista {
    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "nombre")
    private var nombre: String? = null

    @ColumnInfo(name = "sincro")
    private var sincro: Boolean? = null

    fun getSincro(): Boolean? {
        return sincro
    }

    fun setSincro(sincro: Boolean?) {
        this.sincro = sincro
    }

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
}}