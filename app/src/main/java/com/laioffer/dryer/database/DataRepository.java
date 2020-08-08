package com.laioffer.dryer.database;

import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private MySQLConnection conn;
    private List<Washer> washer;
    public DataRepository(){
        washer = new ArrayList<>();
    }
    public LiveData<List<Washer>> getWashers() {
        MutableLiveData<List<Washer>> temp = new MutableLiveData<>();
        new WasherAsyncTask(temp).execute();
        return temp;
    }
    private static class WasherAsyncTask extends AsyncTask<Void, Void, List<Washer>> {
        private final MutableLiveData<List<Washer>> liveData;
        private WasherAsyncTask(MutableLiveData<List<Washer>> liveData) {
            this.liveData = liveData;
        }
        @Override
        protected List<Washer> doInBackground(Void... voids) {
            MySQLConnection conn = new MySQLConnection();
            List<Washer> res = conn.getWashers();
            conn.close();
            return res;
        }

        @Override
        protected void onPostExecute(List<Washer> washers) {
            super.onPostExecute(washers);
            liveData.setValue(washers);
        }
    }
}
