package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.*
import java.util.*

@Serializable
enum class TriviaQuestionDifficulty {

    Any,
    Easy,
    Medium,
    Hard;

    @Serializer(forClass = TriviaQuestionDifficulty::class)
    companion object : KSerializer<TriviaQuestionDifficulty> {

        override val descriptor: SerialDescriptor =
            PrimitiveDescriptor("TriviaQuestionDifficulty", PrimitiveKind.STRING)

        @ExperimentalStdlibApi
        override fun deserialize(decoder: Decoder): TriviaQuestionDifficulty =
            valueOf(decoder.decodeString().capitalize(Locale.ROOT))

        @ImplicitReflectionSerializer
        override fun serialize(encoder: Encoder, value: TriviaQuestionDifficulty) =
            encoder.encode(value.name.toLowerCase(Locale.ROOT))
    }
}
