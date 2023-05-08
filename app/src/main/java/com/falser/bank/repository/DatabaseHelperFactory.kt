package com.falser.bank.repository

import android.content.Context
import com.j256.ormlite.android.apptools.OpenHelperManager


object DatabaseHelperFactory {
    private var _helper: DatabaseHelper? = null

    fun setHelper(context: Context?) {
        _helper = DatabaseHelper(context)
        _helper?.writableDatabase
    }

    val helper: DatabaseHelper
        get() = _helper!!

    fun releaseHelper() {
        OpenHelperManager.releaseHelper()
        _helper = null
    }
}