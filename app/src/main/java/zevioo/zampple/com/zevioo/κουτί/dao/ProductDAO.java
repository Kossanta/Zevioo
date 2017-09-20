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

import zevioo.zampple.com.zevioo.κουτί.entity.Product;
import zevioo.zampple.com.zevioo.κουτί.entity.Profile;
import zevioo.zampple.com.zevioo.κουτί.entity.SimpleItem;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by kgiannoulis on 26/5/2017
 */
@Dao
public interface ProductDAO {

    @Query("select * from Product where prid = :prid")
    Product getProductById(String prid);

    @Query("SELECT * FROM Product ORDER BY rdt DESC")
    LiveData<List<Product>> findAllProducts();

    @Insert(onConflict = IGNORE)
    long insertProduct(Product item);

    @Insert(onConflict = IGNORE)
    void insertAll(List<Product> items);

    @Update(onConflict = REPLACE)
    void updateProduct(Product item);

    @Query("DELETE FROM Product")
    void deleteAllProducts();

    @Query("DELETE from Product where prid = :prid")
    void deleteProductWithItemId(String prid);
}
