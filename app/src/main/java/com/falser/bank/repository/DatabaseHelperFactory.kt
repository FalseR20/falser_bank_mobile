package com.falser.bank.repository

import android.content.Context
import com.j256.ormlite.android.apptools.OpenHelperManager


class DatabaseHelperFactory {
    companion object {
        private var helper: DatabaseHelper? = null

        fun setHelper(context: Context?) {
            helper = DatabaseHelper(context)
            helper?.writableDatabase
        }

        fun getHelper(): DatabaseHelper {
            return helper!!
        }

        fun releaseHelper() {
            OpenHelperManager.releaseHelper()
            helper = null
        }
    }
}