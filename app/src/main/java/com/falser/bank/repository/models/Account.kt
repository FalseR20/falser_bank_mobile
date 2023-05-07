package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
class Account {
    @DatabaseField(generatedId = true)
    var id: Long? = null

    @DatabaseField(foreign = true)
    val currency: Currency? = null

//    @DatabaseField(foreign = true)
//    val user: User? = null  // TODO: add User table

    @DatabaseField
    var balance: Long? = null

    @DatabaseField
    var isClosed: Boolean? = null
}
