package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.math.BigDecimal

@DatabaseTable(tableName = "account")
data class Account(
    @DatabaseField(generatedId = true) var id: Long,
    @DatabaseField var balance: BigDecimal,
    @DatabaseField var isClosed: Boolean,
    @DatabaseField val currencyId: Int,
) {
    @Suppress("unused")
    constructor() : this(0, BigDecimal.ZERO, false, 0)
}
