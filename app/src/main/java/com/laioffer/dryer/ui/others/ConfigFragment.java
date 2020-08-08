package com.laioffer.dryer.ui.others;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.laioffer.dryer.databinding.FragmentConfigBinding;

public class ConfigFragment extends Fragment {
    FragmentConfigBinding binding;
    public static ConfigFragment newInstance(){
        Bundle args = new Bundle();
        ConfigFragment fragment = new ConfigFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentConfigBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
