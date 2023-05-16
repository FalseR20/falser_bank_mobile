package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.Calendar
import java.util.Date
import java.util.Random
import kotlin.math.abs

@DatabaseTable
class Card() {
    @DatabaseField(generatedId = true)
    var id: Long? = null

    @DatabaseField(foreign = true)
    var account: Account? = null

    @DatabaseField(unique = true)
    var number: Long? = null

    @DatabaseField
    var cardholderName: String? = null

    @DatabaseField
    var expirationDate: Date? = null

    @DatabaseField
    var isFrozen: Boolean? = null

    constructor(account: Account, cardholderName: String, yearsSupport: Int) : this() {
        this.account = account
        this.cardholderName = cardholderName
        this.expirationDate = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, getActualMinimum(Calendar.DAY_OF_MONTH))
            set(Calendar.HOUR_OF_DAY, getActualMinimum(Calendar.HOUR_OF_DAY))
            set(Calendar.MINUTE, getActualMinimum(Calendar.MINUTE))
            set(Calendar.SECOND, getActualMinimum(Calendar.SECOND))
            set(Calendar.MILLISECOND, getActualMinimum(Calendar.MILLISECOND))
            add(Calendar.YEAR, yearsSupport)
            add(Calendar.MONTH, 1)
        }.time
        number = abs(Random().nextLong() % 8999_9999_9999_9999L) + 1000_0000_0000_0000L
        // TODO: change
        isFrozen = false
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(number=$number)"
    }

    fun humanNumber(): String {
        return number.toString().chunked(4).joinToString(" ")
    }
}