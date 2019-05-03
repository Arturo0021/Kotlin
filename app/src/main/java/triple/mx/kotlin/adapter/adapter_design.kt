package triple.mx.kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import triple.mx.kotlin.R
import triple.mx.kotlin.inflate
import triple.mx.kotlin.listener.RecyclerDesignListener
import triple.mx.kotlin.Entities.Usuario
import kotlinx.android.synthetic.main.recycler_design.view.*

class DesignAdapter(private val lista: List<Usuario>, private val listener: RecyclerDesignListener)
    : RecyclerView.Adapter<DesignAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.recycler_design))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(lista[position], listener)

    override fun getItemCount() = lista.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(lista: Usuario, listener: RecyclerDesignListener) = with(itemView){
            text_view.text = lista.puesto

            setOnClickListener{listener.onClick(lista, adapterPosition)}
            button_view.setOnClickListener { listener.onDelete(lista, adapterPosition) }
        }
    }


}