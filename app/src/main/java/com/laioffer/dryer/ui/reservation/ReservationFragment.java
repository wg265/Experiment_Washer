package com.laioffer.dryer.ui.reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.laioffer.dryer.databinding.FragmentReservationsBinding;


public class ReservationFragment extends Fragment {
    FragmentReservationsBinding binding;
    public static ReservationFragment newInstance() {
        Bundle args = new Bundle();
        ReservationFragment fragment = new ReservationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReservationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
