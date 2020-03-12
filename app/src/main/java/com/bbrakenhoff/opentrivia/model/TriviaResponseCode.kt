package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.*

enum class TriviaResponseCode {
    Success,
    NoResults,
    InvalidParameter,
    TokenNotFound,
    TokenEmpty;

    @Serializer(forClass = TriviaResponseCode::class)
    companion object : KSerializer<TriviaResponseCode> {

        override val descriptor: SerialDescriptor =
            PrimitiveDescriptor("TriviaQuestionType", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): TriviaResponseCode =
            values()[decoder.decodeInt()]

        override fun serialize(encoder: Encoder, value: TriviaResponseCode) =
            encoder.encode(value.ordinal)
    }
}
