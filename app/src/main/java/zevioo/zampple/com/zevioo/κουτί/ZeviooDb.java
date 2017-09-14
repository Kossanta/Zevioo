package zevioo.zampple.com.zevioo.κουτί;/*
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

import android.Manifest;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.io.File;

import zevioo.zampple.com.zevioo.κουτί.converter.DateConverter;
import zevioo.zampple.com.zevioo.κουτί.dao.ProductDAO;
import zevioo.zampple.com.zevioo.κουτί.dao.ProfileDAO;
import zevioo.zampple.com.zevioo.κουτί.dao.SimpleItemDAO;
import zevioo.zampple.com.zevioo.κουτί.entity.Product;
import zevioo.zampple.com.zevioo.κουτί.entity.Profile;
import zevioo.zampple.com.zevioo.κουτί.entity.SimpleItem;

/**
 * Created by kgiannoulis on 18/8/2017
 * <p>
 * <p>
 * <p>
 * Use database ZeviooDb mDb = ZeviooDb.getDatabase(getApplicationContext());
 * Get all members
 * List<Profile> allProfiles = mDb.profileModel().getAllProfiles();
 * On destroy
 * ZeviooDb.destroyInstance();
 */
@Database(entities = {Profile.class, SimpleItem.class, Product.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class ZeviooDb extends RoomDatabase {

    private static ZeviooDb INSTANCE;

    public abstract ProfileDAO profileModel();

    public abstract SimpleItemDAO simpleItemModel();

    public abstract ProductDAO productModel();


    /**
     * Needs read write permission (only for debuggable version)
     *
     * INSTANCE =
     * Room.inMemoryDatabaseBuilder(context.getApplicationContext(), ZeviooDb.class)
     * .build();
     *
     * @param context
     * @return
     */
    @Nullable
    public static ZeviooDb getDatabase(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        } else {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), ZeviooDb.class, Environment.getExternalStorageDirectory()
                                + File.separator + "Zevioo"
                                + File.separator +
                                "database.db")
                                .build();
            }
            return INSTANCE;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}