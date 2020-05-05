package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.*
import java.util.*

@Serializable
enum class TriviaDifficulty {

    Any,
    Easy,
    Medium,
    Hard;

    @Serializer(forClass = TriviaDifficulty::class)
    companion object : KSerializer<TriviaDifficulty> {

        override val descriptor: SerialDescriptor =
            PrimitiveDescriptor("TriviaDifficulty", PrimitiveKind.STRING)

        @ExperimentalStdlibApi
        override fun deserialize(decoder: Decoder): TriviaDifficulty =
            valueOf(decoder.decodeString().capitalize(Locale.ROOT))

        @ImplicitReflectionSerializer
        override fun serialize(encoder: Encoder, value: TriviaDifficulty) =
            encoder.encode(value.name.toLowerCase(Locale.ROOT))
    }
}
