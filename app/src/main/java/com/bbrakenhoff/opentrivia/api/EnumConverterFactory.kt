package com.bbrakenhoff.opentrivia.api

import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.*

class EnumConverterFactory : Converter.Factory() {

    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        var converter: Converter<*, String>? = null

        if (type is Class<*> && type.isEnum) {
            converter =
                Converter<Enum<*>, String> { value -> getSerializedNameValue(value as Enum<*>) }

        }

        return converter
    }

    private fun <E : Enum<*>> getSerializedNameValue(e: E): String? {
        return e.name.toLowerCase(Locale.ROOT)
    }
}
