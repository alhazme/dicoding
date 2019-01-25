package me.alhaz.footballclubunittesting.helper.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import me.alhaz.footballclubunittesting.model.Database.Favorite
import org.jetbrains.anko.db.*


class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT,
                Favorite.DATE to TEXT,
                Favorite.TEAM_HOME_ID to TEXT,
                Favorite.TEAM_HOME_NAME to TEXT,
                Favorite.TEAM_HOME_SCORE to INTEGER,
                Favorite.TEAM_AWAY_ID to TEXT,
                Favorite.TEAM_AWAY_NAME to TEXT,
                Favorite.TEAM_AWAY_SCORE to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)