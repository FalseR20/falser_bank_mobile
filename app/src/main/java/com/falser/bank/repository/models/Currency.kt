package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import kotlin.math.pow

@DatabaseTable
class Currency() {
    @DatabaseField(generatedId = true)
    var id: Long? = null

    @DatabaseField
    var precision: Int? = null  // 2.46 BYN => value = 246, precision = 2

    @DatabaseField
    var coursePrecision: Int? = null  // 2,8287 BYN/USD => course = 28287, coursePrecision = 4

    @DatabaseField
    var code: String? = null

    @DatabaseField
    var humanFormat: String? = null

    constructor(
        code: String,
        humanFormat: String? = "%s $code",
        precision: Int = 2,
        coursePrecision: Int = 4,
    ) : this() {
        this.code = code
        this.humanFormat = humanFormat
        this.precision = precision
        this.coursePrecision = coursePrecision
    }

    fun valueToString(value: Long): String {
        return precision!!.let { "%.${it}f".format(value / 10f.pow(it)) }
    }

    fun format(value: Long): String {
        return humanFormat!!.format(valueToString(value))
    }
}
