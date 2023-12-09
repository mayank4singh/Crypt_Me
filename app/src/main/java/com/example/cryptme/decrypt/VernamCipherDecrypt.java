package com.example.cryptme.decrypt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.cryptme.R;


public class VernamCipherDecrypt extends Fragment {

    private EditText encryptedText;
    private EditText keyText;
    private Button decryptButton;
    private TextView decryptedText, know;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vernam_cipher_decrypt, container, false);
        encryptedText = view.findViewById(R.id.editTextEncrypted);
        keyText = view.findViewById(R.id.editTextKeyword);
        decryptButton = view.findViewById(R.id.buttonDecrypt);
        decryptedText = view.findViewById(R.id.textViewDecrypted);
        know = view.findViewById(R.id.abt);
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                know.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='https://www.geeksforgeeks.org/vernam-cipher-in-cryptography/'> Vernam Cipher</a>";
                know.setText(Html.fromHtml(text));
            }
        });
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ciphertext = encryptedText.getText().toString().toUpperCase();
                String key = keyText.getText().toString().toUpperCase();
                String decryptedMessage = decryptVernam(ciphertext, key);
                decryptedText.setText(decryptedMessage);
            }
        });

        return view;
    }

    // Vernam cipher decryption method
    private String decryptVernam(String ciphertext, String key) {
        if (ciphertext.length() != key.length()) {
            encryptedText.setError("Ciphertext and key must have the same length.");
        }

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            int decryptedChar = ((ciphertext.charAt(i) - 'A') - (key.charAt(i) - 'A') + 26) % 26 + 'A';
            decrypted.append((char) decryptedChar);
        }

        return decrypted.toString();
    }
}