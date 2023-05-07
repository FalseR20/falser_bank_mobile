package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.sql.Timestamp

@DatabaseTable
class Course() {
    @DatabaseField(generatedId = true)
    var id: Long? = null

    @DatabaseField(foreign = true)
    var currency: Currency? = null

    @DatabaseField
    var value: Long? = null  // TODO: separate into buy and sell

    @DatabaseField
    var time: Timestamp? = null

    constructor(currency: Currency, value: Long) : this() {
        this.currency = currency
        this.value = value
        this.time = Timestamp(System.currentTimeMillis())
    }
}