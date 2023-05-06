package com.falser.bank.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.falser.bank.repository.models.Account
import com.falser.bank.repository.models.Card
import com.falser.bank.repository.models.Course
import com.falser.bank.repository.models.Currency
import com.falser.bank.repository.models.Transaction
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

@Suppress("MemberVisibilityCanBePrivate")
class DatabaseHelper(context: Context?) :
    OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "database.sqlite3"
        const val DATABASE_VERSION = 2
    }

    val accountDao = DaoManager.createDao(connectionSource, Account::class.java)!!
    val cardDao = DaoManager.createDao(connectionSource, Card::class.java)!!
    val courseDao = DaoManager.createDao(connectionSource, Course::class.java)!!
    val currencyDao = DaoManager.createDao(connectionSource, Currency::class.java)!!
    val transactionDao = DaoManager.createDao(connectionSource, Transaction::class.java)!!

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTable(accountDao)
        TableUtils.createTable(cardDao)
        TableUtils.createTable(courseDao)
        TableUtils.createTable(currencyDao)
        TableUtils.createTable(transactionDao)
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