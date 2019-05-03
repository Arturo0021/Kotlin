package triple.mx.kotlin

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class camera_activity : AppCompatActivity() {

    private val REQUESTCAMERAPERMISSION = 100
    private val REQUESTCAMERAREQUEST = 200

    lateinit var button_pick : Button
    lateinit var imageView_pick : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_activity)

        button_pick = findViewById(R.id.button_pick)
        imageView_pick = findViewById(R.id.imageView_pick)

        button_pick.setOnClickListener {
            getPictureFromCameraAskingPermissions()
        }

    }

    private fun getPictureFromCameraAskingPermissions() {
        // Añadir permisos al manifest
        // Comprobar los permisos de la camara
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            // Permisos desde la 6.0 [API 23] en adelante
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUESTCAMERAPERMISSION)
        } else {
            // Si los permisos han sido aceptado previamente de la 6.0 Inferiores de la [API 23]
            intentActivityCamera()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            REQUESTCAMERAPERMISSION -> {
                if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // Permiso Aceptado
                    intentActivityCamera()
                } else {
                    // Permiso Denegado
                    Toast.makeText(this, "Permisos Denegados", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode){
            REQUESTCAMERAREQUEST -> {
                // Comprobamos que el resultado sea bueno
                if(resultCode == Activity.RESULT_OK) {
                    // Obtenemos los extras del intent recibido por parametros
                    val extras = data!!.extras
                    // Bitmap
                    val imageBitmap = extras.get("data") as Bitmap
                    // Cargamos la foto como bitmap en el imageView
                    imageView_pick.setImageBitmap(imageBitmap)
                } else {
                    // La foto NO ha sido tomada
                    Toast.makeText(this, "Error al Tomar la Foto", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun intentActivityCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUESTCAMERAREQUEST)
    }

}
