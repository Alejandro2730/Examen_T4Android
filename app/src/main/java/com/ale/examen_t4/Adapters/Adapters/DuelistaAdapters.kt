package com.ale.examen_t4.Adapters.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comupnquispeaponfinal.Clases.Duelista
import com.example.comupnquispeaponfinal.DuelistasDetallesActivity
import com.example.comupnquispeaponfinal.R


class DuelistaAdapter(duelistas: List<Duelista>, context: Context) :
    RecyclerView.Adapter<Any?>() {
    private var duelistas: List<Duelista>
    private val context: Context

    init {
        this.duelistas = duelistas
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: NameViewHolder
        val inflater = LayoutInflater.from(parent.context)
        viewHolder = if (viewType == 1) {
            val view: View = inflater.inflate(R.layout.item_listaduelista, parent, false)
            NameViewHolder(view)
        } else {
            val view: View = inflater.inflate(R.layout.item_progressbardue, parent, false)
            NameViewHolder(view)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: Duelista = duelistas[position] ?: return
        val view = holder.itemView
        val tvName = view.findViewById<TextView>(R.id.tvnombreD)
        tvName.setText(item.getNombre())
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DuelistasDetallesActivity::class.java)
            intent.putExtra("position", item.getId())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return duelistas.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: Duelista = duelistas[position]
        return if (item == null) 0 else 1
    }

    fun setDuelista(duelistas: List<Duelista>) {
        this.duelistas = duelistas
    }

    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
