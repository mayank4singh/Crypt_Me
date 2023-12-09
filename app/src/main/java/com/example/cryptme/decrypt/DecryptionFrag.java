package com.example.cryptme.decrypt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.cryptme.R;


public class DecryptionFrag extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_decryption, container, false);
        RadioGroup radioGroup = view.findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.csr:
                        CsrCipherDecrypt csrCipherDecrypt = new CsrCipherDecrypt();
                        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,csrCipherDecrypt);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        radioGroup.clearCheck();
                        break;

                    case R.id.vgnr:
                        VigenèreCipherDecrypt vigenèreCipherDecrypt = new VigenèreCipherDecrypt();
                        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container,vigenèreCipherDecrypt);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        radioGroup.clearCheck();
                        break;

                    case R.id.hill:
                        AESDecrypt hillCipherDecrypt = new AESDecrypt();
                        FragmentTransaction transact = requireActivity().getSupportFragmentManager().beginTransaction();
                        transact.replace(R.id.container,hillCipherDecrypt);
                        transact.addToBackStack(null);
                        transact.commit();
                        radioGroup.clearCheck();
                        break;

                    case R.id.vernam:
                        VernamCipherDecrypt vernamCipherDecrypt = new VernamCipherDecrypt();
                        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.container,vernamCipherDecrypt);
                        ft.addToBackStack(null);
                        ft.commit();
                        radioGroup.clearCheck();
                        break;
                }
            }
        });
        return view;
    }
}