package com.laioffer.dryer.ui.washers;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.laioffer.dryer.database.DataRepository;
import com.laioffer.dryer.database.MySQLConnection;
import com.laioffer.dryer.database.Washer;
import com.laioffer.dryer.database.WasherViewModelFacory;
import com.laioffer.dryer.databinding.FragmentWashersBinding;

import java.util.ArrayList;
import java.util.List;

public class WasherFragment extends Fragment {
    private FragmentWashersBinding binding;
    private WasherViewModel viewModel;
    private WasherAdapter washerAdapter;
    public static WasherFragment newInstance() {
        Bundle args = new Bundle();
        WasherFragment fragment = new WasherFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWashersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataRepository repository = new DataRepository();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1);
        washerAdapter = new WasherAdapter();
        binding.washerRecyclerView.setLayoutManager(gridLayoutManager);
        binding.washerRecyclerView.setAdapter(washerAdapter);

        viewModel = new ViewModelProvider(this, new WasherViewModelFacory(repository))
                .get(WasherViewModel.class);
        viewModel.getWashers()
                .observe(
                        getViewLifecycleOwner(),
                        response -> {
                            if (response != null) {
                                washerAdapter.setWashers(response);
                            }
                        }
                );
        SwipeRefreshLayout swipeContainer = binding.swipeContainer;
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getWashers()
                        .observe(
                                getViewLifecycleOwner(),
                                response -> {
                                    if (response != null) {
                                        washerAdapter.setWashers(response);
                                    }
                                }
                        );
                swipeContainer.setRefreshing(false);
            }
        });
    }


}
