package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.sql.Timestamp


@DatabaseTable
class Transaction {

    @DatabaseField(generatedId = true)
    var id: Long? = null

    @DatabaseField(foreign = true)
    var currency: Currency? = null

    @DatabaseField
    var value: Long? = null  // TODO: separate

    @DatabaseField
    var commission: Long? = null  // TODO: add

    @DatabaseField
    var time: Timestamp? = null
}