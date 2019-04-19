package com.coderbot.movies.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

class DatabaseMigrations {

    class Migration_1_2 internal constructor(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {

        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }

}