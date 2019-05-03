package triple.mx.kotlin.listener

import triple.mx.kotlin.Entities.Usuario

interface RecyclerDesignListener {
    fun onClick(lista: Usuario, position: Int)
    fun onDelete(lista: Usuario, position: Int)
}