package ru.yandex.smr.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class ImageProcessor {

    suspend fun process(urlString: String): Bitmap = withContext(Dispatchers.IO) {
        val url = URL(urlString)
        val input = url.openConnection().apply {
            doInput = true
            connect()
        }.inputStream
        return@withContext BitmapFactory.decodeStream(input)
    }
}