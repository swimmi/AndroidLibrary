package net.swimmi.androidlibrary.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class FileUtil {

    fun readText(context: Context, fileName: String): String {
        val sb = StringBuilder()
        try {
            val am = context.assets
            val br = BufferedReader(InputStreamReader(am.open(fileName)))
            var line = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return sb.toString()
    }

    fun loadImage(context: Context, fileName: String): Bitmap? {
        var image: Bitmap? = null
        try {
            val am = context.assets
            val inputStream = am.open(fileName)
            image = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }
}