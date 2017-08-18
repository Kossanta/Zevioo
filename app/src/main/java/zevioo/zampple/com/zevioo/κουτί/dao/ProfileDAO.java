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

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by kgiannoulis on 26/5/2017
 */
@Dao
public interface ProfileDAO {

    @Query("select * from Profile where id = :id")
    Profile getProfileById(int id);

    @Query("select * from Profile where cid = :cid")
    Profile getProfileByCustomerId(String cid);

    @Query("SELECT * FROM Profile")
    List<Profile> getAllProfiles();


    @Query("SELECT * FROM Areas")
    LiveData<List<Profile>> findAllProfiles();


    @Insert(onConflict = IGNORE)
    long insertProfile(Profile profile);

    @Update(onConflict = REPLACE)
    void updateProfile(Profile profile);

    @Query("DELETE FROM Profile")
    void deleteAll();

    @Query("DELETE from Profile where cid = :cid")
    void deleteProfileWithCustomerId(String cid);
}
