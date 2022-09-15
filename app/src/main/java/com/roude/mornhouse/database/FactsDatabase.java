package com.roude.mornhouse.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.roude.mornhouse.dao.FactDao;
import com.roude.mornhouse.model.Fact;


@Database(entities = {Fact.class}, version =  1)
public abstract class FactsDatabase extends RoomDatabase {
    public abstract FactDao getFactDao();
}
