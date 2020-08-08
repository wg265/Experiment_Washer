package com.laioffer.dryer.ui.others;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.laioffer.dryer.databinding.FragmentExperimentBinding;

public class ExperimentFragment extends Fragment {
    FragmentExperimentBinding binding;
    public static ExperimentFragment newInstance() {
        Bundle args = new Bundle();
        ExperimentFragment fragment = new ExperimentFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExperimentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
