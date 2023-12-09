package com.example.cryptme.encrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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


public class VigenèreCipherEncrypt extends Fragment {

    private EditText plainText;
    private EditText keyword;
    private Button encryptButton;
    private TextView encryptedText, more;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vigenere_cipher, container, false);
        plainText = view.findViewById(R.id.editTextPlainText);
        keyword = view.findViewById(R.id.editTextKeyword);
        encryptButton = view.findViewById(R.id.buttonEncrypt);
        encryptedText = view.findViewById(R.id.textViewEncrypted);
        encryptedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = encryptedText.getText().toString();
                copyTextToClipboard(textToCopy);
            }
        });
        more = view.findViewById(R.id.abt);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='https://www.geeksforgeeks.org/vigenere-cipher/'> Vigenere Cipher</a>";
                more.setText(Html.fromHtml(text));
            }
        });

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = plainText.getText().toString().toUpperCase().replaceAll("[^A-Z]", "");
                String key = keyword.getText().toString().toUpperCase().replaceAll("[^A-Z]", "");
                String encryptedMessage = encryptVigenere(text, key);
                encryptedText.setText(encryptedMessage);
            }
        });

        return view;
    }

    // Encrypts the given text using Vigenère cipher
    private String encryptVigenere(String text, String key) {
        StringBuilder encrypted = new StringBuilder();
        int keyLen = key.length();
        int textLen = text.length();

        for (int i = 0; i < textLen; i++) {
            char plainChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLen);
            int encryptedChar = ((plainChar + keyChar) % 26) + 'A';
            encrypted.append((char) encryptedChar);
        }

        return encrypted.toString();
    }
    private void copyTextToClipboard(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Copied Text", text);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipData);
            // Optionally, you can show a message that the text has been copied
            // Toast.makeText(requireContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }
}
