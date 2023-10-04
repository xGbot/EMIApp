package com.example.emi;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.emi.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import java.net.InetSocketAddress;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check whether all views are valid
                if (binding.editTextMortgage.getText().toString().trim().isEmpty() || 
                Integer.parseInt(binding.editTextMortgage.getText().toString()) < 20000 || 
                Integer.parseInt(binding.editTextMortgage.getText().toString()) > 9000000){
                    binding.textViewHintMortgage.setVisibility(View.VISIBLE);
                }
                else if (binding.editTextRate.getText().toString().trim().isEmpty() ||
                Integer.parseInt(binding.editTextRate.getText().toString()) <= 0 ||
                Integer.parseInt(binding.editTextRate.getText().toString()) > 30) {

                    binding.textViewHintInterest.setVisibility(View.VISIBLE);
                }
                else {
                    // Bundle all view information to send to next activity for calculation
                    Bundle bundle = new Bundle();
                    bundle.putDouble("mortgage", Double.parseDouble((binding.editTextMortgage.getText().toString())));
                    bundle.putDouble("rate", Double.parseDouble(binding.editTextRate.getText().toString()));
                    bundle.putInt("period", binding.spinnerPeriod.getSelectedItemPosition() + 1);

                    // Intent sent to start next activity with bundle
                    Intent intent = new Intent(getActivity(), SecondActivity.class);
                    intent.putExtras(bundle);
                    getActivity().finish();
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}