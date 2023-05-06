package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.sql.Timestamp


@DatabaseTable(tableName = "transaction")
class Transaction(
    @DatabaseField(generatedId = true) var id: Long,
    @DatabaseField(foreign = true) var currency: Currency,
    @DatabaseField var value: Long,  // TODO: separate
//    @DatabaseField var commission: Long,  // TODO: add
    @DatabaseField var time: Timestamp,

    ) {
    @Suppress("unused")
    constructor() : this(0, Currency(), 0, Timestamp(0))
}