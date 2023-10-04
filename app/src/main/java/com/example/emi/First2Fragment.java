package com.example.emi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.emi.databinding.FragmentFirst2Binding;

import java.text.DecimalFormat;

public class First2Fragment extends Fragment {

private FragmentFirst2Binding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirst2Binding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Receive intent from MainActivity
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        // Retrieve information from bundle
        double mortgage = bundle.getDouble("mortgage");
        double rate = bundle.getDouble("rate");
        int period = bundle.getInt("period");

        // Calculate monthly interest rate
        rate = rate / 100;
        double monthlyIR = rate / 12;
        // Calculate total # of payments based on amortization period
        int numPayments = period * 12;

        // Use EMI formula
        // EMI = P * r * (Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1)
        // EMI = Mortgage * Rate * (irPay)
        double irPay = Math.pow(1 + monthlyIR, numPayments);
        double EMI = (mortgage * monthlyIR * irPay) / (irPay - 1);

        // Show answer with 2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        String emiRounded = df.format(EMI);

        // Set the EditText view with the answer
        binding.textviewCalculation.setText("Monthly Payment: \n $" + emiRounded);
        binding.textviewCalculation.setVisibility(View.VISIBLE);

        binding.buttonReturn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // Finish this activity and start activity for the MainActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}