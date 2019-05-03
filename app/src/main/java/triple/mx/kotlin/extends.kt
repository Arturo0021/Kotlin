package triple.mx.kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!
