package triple.mx.kotlin

import android.content.ContentProviderClient
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import triple.mx.kotlin.Entities.*

class Main_Activity : AppCompatActivity() {

    //Tipos de Datos
    private var cadena: String   = "Ivan Arturo"
    private var char: Char       = 'a'
    private var entero: Int      = 123
    private var byte: Byte       = 1
    private var decimal: Double  = 1.0
    private var float: Float     = 2.0f
    private var boolean: Boolean = true
    private var arrayenteros     = arrayOf(1,2,3,4,5,6)

    lateinit var button_go_location: Button
    lateinit var button_listview: Button
    lateinit var button_camera: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_)

        val edit_text: TextInputEditText = findViewById(R.id.edit_text)
        val text_view: TextView = findViewById(R.id.text_view)
        val button_view: TextView = findViewById(R.id.button_view)
        button_go_location = findViewById(R.id.button_go_location)
        button_listview = findViewById(R.id.button_listview)
        button_camera = findViewById(R.id.button_camera)

        edit_text.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text_view.setText(s)
            }

        })

        button_view.setOnClickListener {
            Toast.makeText(this, edit_text.text, Toast.LENGTH_LONG).show()
        }

        button_go_location.setOnClickListener {
            val intent = Intent(this, geofence_activity::class.java)
                    startActivity(intent)
        }

        button_listview.setOnClickListener {
            val intent = Intent(this, list_view::class.java)
                    startActivity(intent)
        }

        button_camera.setOnClickListener {
            val intent = Intent(this, camera_activity::class.java)
                    startActivity(intent)
        }

        val arraylist = ArrayList<Usuario>()
        val design = Usuario()
            design.id = 1
            design.puesto = "programador"
            design.usuario = "arturo"
        arraylist.add(design)

        val design2 = Usuario()
            design2.id = 2
            design2.puesto = "programador"
            design2.usuario = "Sergio"
        arraylist.add(design2)

        val salarios = Salarios()
            salarios.id = 1
            salarios.idUsuario = 2
            salarios.salario = 30000.0

        for (prop in arraylist){
            Log.v("#TAG::", "Usuario " + prop.usuario + " Puesto " + prop.puesto)
        }

        Log.e("#TAG:: ", "salario =  ${salarios.salario}, IdUsuario = ${salarios.idUsuario}")

        loquesea(true)
    }

    fun loquesea(valor : Boolean): Boolean {


        return valor
    }

}
