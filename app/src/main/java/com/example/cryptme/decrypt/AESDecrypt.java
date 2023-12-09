package com.example.cryptme.decrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.util.Base64;
import com.example.cryptme.R;
import com.example.cryptme.SharedViewModel;



import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class AESDecrypt extends Fragment {
    private EditText encryptedText;
    private TextView decryptedText, know;
    private Button decryptButton;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aes_decrypt, container, false);
        encryptedText = view.findViewById(R.id.DecText);
        decryptedText = view.findViewById(R.id.Decrslt);
        decryptButton = view.findViewById(R.id.Decryptbtn);

        know = view.findViewById(R.id.abt);
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                know.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href = 'https://www.geeksforgeeks.org/advanced-encryption-standard-aes/'> Advanced Encryption Standard (AES) </a>";
                know.setText(Html.fromHtml(text));
            }
        });
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encryptedBase64 = encryptedText.getText().toString();
                try {
                    byte[] encryptedBytes = Base64.decode(encryptedBase64, Base64.DEFAULT);

                    SecretKey secretKey = sharedViewModel.getSecretKey();

                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

                    // Convert the decrypted bytes back to a readable string
                    String decryptedTextString = new String(decryptedBytes);

                    // Display the decrypted text in the TextView
                    decryptedText.setText(decryptedTextString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
