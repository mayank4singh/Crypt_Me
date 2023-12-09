package com.example.cryptme.encrypt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.cryptme.R;


public class EncryptionFrag extends Fragment  {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_encryption, container, false);
        RadioGroup radioGroup = view.findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.csrcpr:
                         CsrCipherEncrypt csrCipherEncrypt = new CsrCipherEncrypt();
                         FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                         fragmentTransaction.replace(R.id.container,csrCipherEncrypt);
                         fragmentTransaction.addToBackStack(null);
                         fragmentTransaction.commit();
                         radioGroup.clearCheck();
                         break;

                    case R.id.vgncphr:
                         VigenèreCipherEncrypt vigenèreCipherEncrypt = new VigenèreCipherEncrypt();
                         FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                         transaction.replace(R.id.container, vigenèreCipherEncrypt);
                         transaction.addToBackStack(null);
                         transaction.commit();
                         radioGroup.clearCheck();
                         break;

                    case R.id.hillcphr:
                         AESEncrypt hillCipher = new AESEncrypt();
                         FragmentTransaction transact = requireActivity().getSupportFragmentManager().beginTransaction();
                         transact.replace(R.id.container,hillCipher);
                         transact.addToBackStack(null);
                         transact.commit();
                         radioGroup.clearCheck();
                         break;

                    case R.id.vrnmcphr:
                         VernamCipherEncrypt vernamCipherEncrypt = new VernamCipherEncrypt();
                         FragmentTransaction frag = requireActivity().getSupportFragmentManager().beginTransaction();
                         frag.replace(R.id.container,vernamCipherEncrypt);
                         frag.addToBackStack(null);
                         frag.commit();
                         radioGroup.clearCheck();
                         break;

                }
            }
        });
        return view;



    }

    }

