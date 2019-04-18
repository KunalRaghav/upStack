package com.krsolutions.upstack.Database;

import com.krsolutions.upstack.Database.Tag.Tag;
import com.krsolutions.upstack.Database.Tag.TagDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Tag.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TagDao tagDAO();

    public static final String NAME="AppDatabase";
}
