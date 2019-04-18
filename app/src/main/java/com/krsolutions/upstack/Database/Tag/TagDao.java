package com.krsolutions.upstack.Database.Tag;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TagDao {
    @Query("select * from Tag where id= :id;")
    public Tag getById(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertTag(Tag tag);

    @Delete
    public void deleteTag(Tag tag);

    @Query("select * from Tag order by name asc;")
    public List<Tag> getAllTags();

    @Query("delete from Tag;")
    public void deleteAll();
}
