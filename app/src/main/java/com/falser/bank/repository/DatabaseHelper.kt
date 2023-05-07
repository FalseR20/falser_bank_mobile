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
        const val DATABASE_VERSION = 11
    }

    val currencyDao = DaoManager.createDao(connectionSource, Currency::class.java)!!
    val courseDao = DaoManager.createDao(connectionSource, Course::class.java)!!
    val accountDao = DaoManager.createDao(connectionSource, Account::class.java)!!
    val cardDao = DaoManager.createDao(connectionSource, Card::class.java)!!
    val transactionDao = DaoManager.createDao(connectionSource, Transaction::class.java)!!

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTable(currencyDao)
        val bynCurrency = Currency("BYN", coursePrecision = 0)
        val usdCurrency = Currency("USD", "$%s")
        val euroCurrency = Currency("EUR", "%s €")
        currencyDao.create(mutableListOf(bynCurrency, usdCurrency, euroCurrency))

        TableUtils.createTable(courseDao)
        val bynCourse = Course(bynCurrency, 1)
        val usdCourse = Course(usdCurrency, 28287)
        val euroCourse = Course(euroCurrency, 31265)
        courseDao.create(mutableListOf(bynCourse, usdCourse, euroCourse))

        TableUtils.createTable(accountDao)
        TableUtils.createTable(cardDao)
        TableUtils.createTable(transactionDao)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int,
    ) {
        TableUtils.dropTable(transactionDao, false)
        TableUtils.dropTable(cardDao, false)
        TableUtils.dropTable(accountDao, false)
        TableUtils.dropTable(courseDao, false)
        TableUtils.dropTable(currencyDao, false)
        onCreate(database)
    }
}