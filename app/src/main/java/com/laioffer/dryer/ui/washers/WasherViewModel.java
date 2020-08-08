package com.laioffer.dryer.ui.washers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.laioffer.dryer.database.DataRepository;
import com.laioffer.dryer.database.Washer;

import java.util.List;

public class WasherViewModel extends ViewModel {
    private DataRepository repository;
    public WasherViewModel(DataRepository repository) {
        this.repository = repository;
    }
    public LiveData<List<Washer>> getWashers() {
        return repository.getWashers();
    }
}
