package com.example.eunoia.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseModel(
    var id: String = "",
    @SerialName("created_at") var createdAt: String = ""
)
