package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.*

@Serializable
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

        @ImplicitReflectionSerializer
        override fun serialize(encoder: Encoder, value: TriviaResponseCode) =
            encoder.encode(value.ordinal)
    }
}
