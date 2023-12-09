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


public class VigenèreCipherDecrypt extends Fragment {

    private EditText encryptedText;
    private EditText keyword;
    private Button decryptButton;
    private TextView decryptedText, more;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vigenere_cipher_decrypt, container, false);
        encryptedText = view.findViewById(R.id.editTextEncrypted);
        keyword = view.findViewById(R.id.editTextKeyword);
        decryptButton = view.findViewById(R.id.buttonDecrypt);
        decryptedText = view.findViewById(R.id.textViewDecrypted);
        more = view.findViewById(R.id.abt);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='https://www.geeksforgeeks.org/vigenere-cipher/'> Vigenere Cipher</a>";
                more.setText(Html.fromHtml(text));
            }
        });
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = encryptedText.getText().toString().toUpperCase().replaceAll("[^A-Z]", "");
                String key = keyword.getText().toString().toUpperCase().replaceAll("[^A-Z]", "");
                String decryptedMessage = decryptVigenere(text, key);
                decryptedText.setText(decryptedMessage);
            }
        });

        return view;
    }

    // Decrypts the given text using Vigenère cipher
    private String decryptVigenere(String text, String key) {
        StringBuilder decrypted = new StringBuilder();
        int keyLen = key.length();
        int textLen = text.length();

        for (int i = 0; i < textLen; i++) {
            char encryptedChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLen);
            int decryptedChar = ((encryptedChar - keyChar + 26) % 26) + 'A';
            decrypted.append((char) decryptedChar);
        }

        return decrypted.toString();
    }
}