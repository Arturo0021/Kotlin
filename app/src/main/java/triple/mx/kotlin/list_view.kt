package triple.mx.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import triple.mx.kotlin.Entities.Usuario
import triple.mx.kotlin.adapter.users_adapter

class list_view : AppCompatActivity() {

    lateinit var lista_principal: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        lista_principal = findViewById(R.id.lista_principal)
        lista_principal.adapter = users_adapter(this, R.layout.users_activity, getUsuarios())
    }

    private fun getUsuarios() : ArrayList<Usuario> {

        val arraylist = ArrayList<Usuario>()
        val usuario1 = Usuario()
            usuario1.id = 1
            usuario1.puesto = "programador Android"
            usuario1.usuario = "Arturo"
        arraylist.add(usuario1)

        val usuario2 = Usuario()
            usuario2.id = 2
            usuario2.puesto = "programador Android"
            usuario2.usuario = "Sergio"
        arraylist.add(usuario2)

        val usuario3 = Usuario()
            usuario3.id = 3
            usuario3.puesto = "programador Web"
            usuario3.usuario = "Fernando"
        arraylist.add(usuario3)

        val usuario4 = Usuario()
            usuario4.id = 4
            usuario4.puesto = "Lider de Proyecto"
            usuario4.usuario = "Mausan"
        arraylist.add(usuario4)

        return arraylist
    }
}
