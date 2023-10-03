package it.leva.data.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import it.leva.data.dto.CharacterDataWrapperDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

object ResourceHelper {
    fun getFile(filename: String): CharacterDataWrapperDTO {
        val stream = this.javaClass.classLoader?.getResourceAsStream(filename)
        val reader = BufferedReader(InputStreamReader(stream))

        stream?.let {
            val listType: Type = object : TypeToken<CharacterDataWrapperDTO>() {}.type
            return Gson().fromJson(reader, listType)
        }

        return CharacterDataWrapperDTO(null, null, null)
    }
}