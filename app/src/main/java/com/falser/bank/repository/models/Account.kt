package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "account")
class Account(
    @DatabaseField(generatedId = true) var id: Long,
    @DatabaseField(foreign = true) val currency: Currency,
//    @DatabaseField(foreign = true) val user: User,  // TODO: add User table
    @DatabaseField var balance: Long,
    @DatabaseField var isClosed: Boolean,
) {
    @Suppress("unused")
    constructor() : this(0, Currency(), 0, false)
}
