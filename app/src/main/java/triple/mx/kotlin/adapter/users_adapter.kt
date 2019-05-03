package triple.mx.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.users_activity.view.*
import triple.mx.kotlin.Entities.Usuario

class users_adapter(val context: Context, val layout: Int, val list: List<Usuario>) : BaseAdapter() {

   private val view_Inflater : LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val vh: viewHolder

            if(convertView == null) {
                view = view_Inflater.inflate(layout, parent, false)
                vh = viewHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as viewHolder
            }

            vh.letter.text = "${list[position].usuario.substring(0,1)}"
            vh.name.text   = "${list[position].usuario}"
            vh.puesto.text = "${list[position].puesto}"

        return view
    }

}

private class viewHolder(view: View) {
    val letter : TextView = view.text_avatar
    val name   : TextView = view.text_title
    val puesto : TextView = view.text_desc
}
