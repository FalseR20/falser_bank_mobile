package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.Date

@DatabaseTable
class Card {
    @DatabaseField(generatedId = true)
    var id: Long? = null

    @DatabaseField(foreign = true)
    val account: Account? = null

    @DatabaseField(unique = true)
    var number: Long? = null

    @DatabaseField
    var cardholderName: String? = null

    @DatabaseField
    var expirationDate: Date? = null

    @DatabaseField
    val isFrozen: Boolean? = null
}