package com.roude.mornhouse.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.roude.mornhouse.model.Fact;

import java.util.List;

@Dao
public interface FactDao {
    @Query("SELECT * FROM fact")
    List<Fact> getAll();
    //@Query("SELECT * FROM fact WHERE id IN (:songIds)")
    //List<Fact> loadAllBySongId(int... songIds);
    ///@Query("SELECT * FROM fact WHERE name LIKE :name AND release_year = :year LIMIT 1")
    //Fact loadOneByNameAndReleaseYear(String first, int year);
    @Insert
    void insertAll(Fact... facts);
    @Delete
    void delete(Fact fact);
}
