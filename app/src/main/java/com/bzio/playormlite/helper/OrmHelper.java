package com.bzio.playormlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bzio.playormlite.item.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by pheerawat on 20/9/2558.
 */
public class OrmHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "play_ormlite.db";
    private static final int DATABASE_VERSION = 1;

    protected RuntimeExceptionDao<User, Integer> userRunTimeExceptionDao = null;

    public OrmHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public RuntimeExceptionDao<User, Integer> getUserRunTimeExceptionDao() {
        if (userRunTimeExceptionDao == null){
            userRunTimeExceptionDao = getRuntimeExceptionDao(User.class);
        }
        return userRunTimeExceptionDao;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
        userRunTimeExceptionDao = null;
    }
}
