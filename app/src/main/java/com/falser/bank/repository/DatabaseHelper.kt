package com.falser.bank.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.falser.bank.repository.models.Account
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

class DatabaseHelper(context: Context?) :
    OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "database.sqlite3"
        const val DATABASE_VERSION = 1
    }

    val accountDao: Dao<Account, *> = DaoManager.createDao(connectionSource, Account::class.java)

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTable(accountDao)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int,
    ) {
        TableUtils.dropTable(accountDao, false)
        onCreate(database, connectionSource)
    }
}