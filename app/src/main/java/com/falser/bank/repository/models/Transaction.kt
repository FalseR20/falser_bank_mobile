package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.sql.Timestamp


@DatabaseTable
class Transaction() {

    @DatabaseField(generatedId = true)
    var id: Long? = null

    @DatabaseField(foreign = true)
    var card: Card? = null

    @DatabaseField(foreign = true)
    var currency: Currency? = null

    @DatabaseField
    var value: Long? = null

    @DatabaseField
    var description: String? = null

    @DatabaseField
    var time: Timestamp? = null

    @DatabaseField
    var isSuccess: Boolean? = null

    constructor(
        currency: Currency,
        card: Card,
        value: Long,
        description: String,
    ) : this() {
        this.currency = currency
        this.card = card
        this.value = value
        this.description = description
        this.time = Timestamp(System.currentTimeMillis())
        this.isSuccess = false
    }
}