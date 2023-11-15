package com.ale.examen_t4.Adapters.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comupnquispeaponfinal.Clases.Cartas
import com.example.comupnquispeaponfinal.MapsActivity
import com.example.comupnquispeaponfinal.R
import com.squareup.picasso.Picasso

package com.example.comupnquispeaponfinal.Adapters
import com.example.comupnquispeaponfinal.Clases.Cartas
import com.example.comupnquispeaponfinal.DetalleCartaActivity
import com.example.comupnquispeaponfinal.MapsActivity
import com.example.comupnquispeaponfinal.R
import com.squareup.picasso.Picasso

class CartasAdapter(items: List<Cartas>, context: Context) :
    RecyclerView.Adapter<Any?>() {
    private var cartas: List<Cartas>
    var context: Context

    init {
        cartas = items
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: com.example.comupnquispeaponfinal.Adapters.CartasAdapter.NameViewHolder
        val inflater = LayoutInflater.from(parent.context)
        viewHolder = if (viewType == 1) {
            val view: View = inflater.inflate(R.layout.item_carta, parent, false)
            com.example.comupnquispeaponfinal.Adapters.CartasAdapter.NameViewHolder(view)
        } else {
            val view: View = inflater.inflate(R.layout.item_progressbardue, parent, false)
            com.example.comupnquispeaponfinal.Adapters.CartasAdapter.NameViewHolder(view)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val carta: Cartas = cartas[position] ?: return
        val view = holder.itemView
        val NombreC = view.findViewById<TextView>(R.id.NombreCarta)
        val AtaqueC = view.findViewById<TextView>(R.id.AtaqueCarta)
        val DefensaC = view.findViewById<TextView>(R.id.DefensaCarta)
        val img = view.findViewById<ImageView>(R.id.Cartaimagen)
        NombreC.setText(carta.getNombre())
        AtaqueC.text = "Ataque: " + carta.getPuntosdeataque()
        DefensaC.text = "Defensa: " + carta.getPuntosdedefensa()
        Picasso.get().load(carta.getImagen())
            .resize(300, 400) //tamaño específico
            .into(img)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("position", carta.getId())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cartas.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: Cartas = cartas[position]
        return if (item == null) 0 else 1
    }

    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setCartas(carta: List<Cartas>) {
        cartas = carta
    }
}