package net.swimmi.androidlibrary.utils

import android.content.Context
import android.graphics.Bitmap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.swimmi.androidlibrary.model.Treasure

class DataUtil(var context: Context) {

    private var gson: Gson = Gson()


    fun getTreasure(): List<Treasure> {
        val jsonStr = FileUtil().readText(context, "treasure.json")
        return gson.fromJson<MutableList<Treasure>?>(jsonStr, object :TypeToken<List<Treasure>>(){}.type)!!.toList()
    }

    fun getTreasureImage(id: Int): Bitmap {
        var image = FileUtil().loadImage(context, "images/$id.jpg")
        if (image == null)
            image = FileUtil().loadImage(context, "images/0.jpg")
        return image!!
    }
}