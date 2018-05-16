package com.example.ayushgupta.ktmy3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.io.File

class MyAdapter : BaseAdapter {

    var file: File? = null
    var mActivity: MainActivity? = null
    var files: Array<File>? = null
    constructor(mActivity: MainActivity){
        this.mActivity = mActivity
        var path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/"
        file = File(path)
        if (!file!!.exists()){
            path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/"
            file = File(path)
        }
        files = file!!.listFiles()
    }
    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return Long.MAX_VALUE
    }

    override fun getCount(): Int {
        return files!!.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(mActivity)
        val v:View = inflater.inflate(R.layout.list_view, null)
        val iv1 = v.findViewById<ImageView>(R.id.iv1)
        val tv1 = v.findViewById<TextView>(R.id.tv1)
        val tv2 = v.findViewById<TextView>(R.id.tv2)
        val iv2 = v.findViewById<ImageView>(R.id.iv2)
        val f: File = files!![position]
        val bitmap = BitmapFactory.decodeFile(f.path)
        val bitmap1 = ThumbnailUtils.extractThumbnail(bitmap,40,40)
        iv1.setImageBitmap(bitmap1)
        tv1.text = f.name
        tv2.text = (f.length() / 1024).toString() + " kb"
        iv2.setOnClickListener {
            f.delete()
            files = file!!.listFiles()
            this@MyAdapter.notifyDataSetChanged()
        }
        return v
    }
}