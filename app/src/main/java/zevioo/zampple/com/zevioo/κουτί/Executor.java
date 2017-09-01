package zevioo.zampple.com.zevioo.κουτί;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import zevioo.zampple.com.zevioo.κουτί.entity.Profile;
import zevioo.zampple.com.zevioo.κουτί.entity.SimpleItem;


/**
 * Created by kgiannoulis on 18/8/2017
 */

public class Executor {

    ZeviooDb mDb;
    Result mResult;

    public Executor(Context context, Result result) {
        mDb = ZeviooDb.getDatabase(context);
        this.mResult= result;
    }

    /**
     * Profile actions
     */

    public void addProfile(final Profile item){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mResult.insertedOk(mDb.profileModel().insertProfile(item));
                return null;
            }
        };
        task.execute();
    }

    public void deleteProfile(final String cid){
        AsyncTask task = new AsyncTask() {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mResult.actionOk();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                mDb.profileModel().deleteProfileWithCustomerId(cid);
                return null;
            }
        };
        task.execute();
    }

    public void editProfile(final Profile item){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mDb.profileModel().updateProfile(item);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mResult.actionOk();
            }
        };
        task.execute();
    }

    public void getProfile(final String cid) {

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mResult.onResultItem(mDb.profileModel().getProfileByCustomerId(cid));
                return null;
            }
        };
        task.execute();
    }

    public void getAllProfiles() {

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mResult.onResultList(mDb.profileModel().getAllProfiles());
                return null;
            }
        };
        task.execute();
    }

    /**
     * SimpleItem actions (languages, countries)
     */

    public void addSimpleItem(final SimpleItem item){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mResult.insertedOk(mDb.simpleItemModel().insertSimpleItem(item));
                return null;
            }
        };
        task.execute();
    }

    public void editSimpleItem(final Profile item){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mDb.profileModel().updateProfile(item);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mResult.actionOk();
            }
        };
        task.execute();
    }

    public void getSimpleItem(final String cid) {

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mResult.onResultItem(mDb.simpleItemModel().getSimpleItemByItemId(cid));
                return null;
            }
        };
        task.execute();
    }

    public void getAllCountries() {

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mResult.onResultList(mDb.simpleItemModel().getAllCountries());
                return null;
            }
        };
        task.execute();
    }

    public void getAllLanguages() {

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mResult.onResultList(mDb.simpleItemModel().getAllLanguages());
                return null;
            }
        };
        task.execute();
    }
    public void deleteAllLanguages() {

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mDb.simpleItemModel().deleteAllLanguages();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mResult.actionOk();
            }
        };
        task.execute();
    }
    public void deleteAllCountries() {

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mDb.simpleItemModel().deleteAllCountries();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mResult.actionOk();
            }
        };
        task.execute();
    }

    public void addAll(final ArrayList<SimpleItem> list) {

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mDb.simpleItemModel().insertAll(list);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mResult.actionOk();
            }
        };
        task.execute();
    }


    public interface Result {
        void onResultList(List listResult);
        void onResultItem(Object item);
        void insertedOk(long insertedId);
        void actionOk();
    }
}
