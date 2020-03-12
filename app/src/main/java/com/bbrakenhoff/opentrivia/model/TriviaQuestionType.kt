package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.*
import java.util.*

enum class TriviaQuestionType {

    Multiple,
    Boolean;

    @Serializer(forClass = TriviaQuestionType::class)
    companion object : KSerializer<TriviaQuestionType> {

        override val descriptor: SerialDescriptor =
            PrimitiveDescriptor("TriviaQuestionType", PrimitiveKind.STRING)

        @ExperimentalStdlibApi
        override fun deserialize(decoder: Decoder): TriviaQuestionType =
            TriviaQuestionType.valueOf(decoder.decodeString().capitalize(Locale.ROOT))

        override fun serialize(encoder: Encoder, value: TriviaQuestionType) =
            encoder.encode(value.name.toLowerCase(Locale.ROOT))
    }
}
