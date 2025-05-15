package com.example.tryoutpas_29_19;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    private Button btnPremier;
    private Button btnLaLiga;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        btnPremier = view.findViewById(R.id.btn_premier);
        btnLaLiga = view.findViewById(R.id.btn_laliga);

        btnPremier.setOnClickListener(v -> openViewFragment("English Premier League"));
        btnLaLiga.setOnClickListener(v -> openViewFragment("Spanish La Liga"));

        return view;
    }

    private void openViewFragment(String leagueName) {
        Bundle bundle = new Bundle();
        bundle.putString("LEAGUE_NAME", leagueName);

        TmpilFragment tmpilFragment = new TmpilFragment();
        tmpilFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, tmpilFragment)
                .addToBackStack(null)
                .commit();
    }
}
