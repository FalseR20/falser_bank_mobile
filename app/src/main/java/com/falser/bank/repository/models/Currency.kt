package com.falser.bank.repository.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
class Currency() {
    @DatabaseField(generatedId = true)
    var id: Long? = null

    @DatabaseField
    var precision: Int? = null  // 2.46 BYN => balance = 246, precision = 2


    @DatabaseField
    var coursePrecision: Int? = null  // 2,8287 BYN/USD => value = 28287, precision = 4

    @DatabaseField
    var code: String? = null

    @DatabaseField
    var specialFormat: String? = null

    constructor(
        code: String,
        specialFormat: String? = "%s $code",
        precision: Int = 2,
        coursePrecision: Int = 4,
    ) : this() {
        this.code = code
        this.specialFormat = specialFormat
        this.precision = precision
        this.coursePrecision = coursePrecision
    }
}