package com.laioffer.dryer.database;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.laioffer.dryer.ui.washers.WasherViewModel;

public class WasherViewModelFacory implements ViewModelProvider.Factory {
    private final DataRepository repository;
    public WasherViewModelFacory(DataRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WasherViewModel.class)) {
            return (T) new WasherViewModel(repository);
        }
        else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }
}
