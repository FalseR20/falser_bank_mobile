package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.Date

@DatabaseTable(tableName = "card")
class Card(
    @DatabaseField(generatedId = true) var id: Long,
    @DatabaseField(unique = true) var number: Long,
    @DatabaseField var cardholderName: String,
    @DatabaseField var expirationDate: Date,
    @DatabaseField val isFrozen: Boolean,
    @DatabaseField val accountId: Long,
//    @DatabaseField val clientId: Long,
) {
    @Suppress("unused")
    constructor() : this(0, 0, "CARDHOLDER_NAME", Date(), false, 0)
}
