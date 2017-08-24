/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zevioo.zampple.com.zevioo.κουτί.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import zevioo.zampple.com.zevioo.κουτί.entity.Profile;
import zevioo.zampple.com.zevioo.κουτί.entity.SimpleItem;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by kgiannoulis on 26/5/2017
 */
@Dao
public interface SimpleItemDAO {

    @Query("select * from SimpleItem where id = :id")
    SimpleItem getSimpleItemById(int id);

    @Query("select * from SimpleItem where item_id = :item_id")
    SimpleItem getSimpleItemByItemId(String item_id);

    @Query("SELECT * FROM SimpleItem where type = 2")
    List<SimpleItem> getAllCountries();

    @Query("SELECT * FROM SimpleItem where type = 1")
    List<SimpleItem> getAllLanguages();

    @Query("SELECT * FROM SimpleItem where type = 2")
    LiveData<List<SimpleItem>> findAllCountries();

    @Query("SELECT * FROM SimpleItem where type = 1")
    LiveData<List<SimpleItem>> findAllLanguages();

    @Insert(onConflict = IGNORE)
    long insertSimpleItem(SimpleItem item);

    @Insert(onConflict = IGNORE)
    void insertAll(List<SimpleItem> items);

    @Update(onConflict = REPLACE)
    void updateSimpleItem(SimpleItem item);

    @Query("DELETE FROM SimpleItem where type = 2")
    void deleteAllCountries();

    @Query("DELETE FROM SimpleItem where type = 1")
    void deleteAllLanguages();

    @Query("DELETE FROM SimpleItem")
    void deleteAllSimpleItems();

    @Query("DELETE from SimpleItem where item_id = :item_id")
    void deleteSimpleItemWithItemId(String item_id);
}
