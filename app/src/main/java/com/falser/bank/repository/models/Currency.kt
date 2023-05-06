package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "currency")
class Currency(
    @DatabaseField(generatedId = true) var id: Long,
    @DatabaseField var precision: Int,
    @DatabaseField var code: String,
    @DatabaseField var specialFormat: String,
) {
    @Suppress("unused")
    constructor() : this(0, 0, "ABC", "%d ABC")
}