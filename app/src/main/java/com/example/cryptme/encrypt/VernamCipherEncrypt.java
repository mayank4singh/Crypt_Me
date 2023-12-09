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


public class VernamCipherEncrypt extends Fragment {
    private EditText plainText;
    private EditText keyText;
    private Button encryptButton;
    private TextView encryptedText, know;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vernam_cipher_encrypt, container, false);
        plainText = view.findViewById(R.id.editTextPlainText);
        keyText = view.findViewById(R.id.editTextKey);
        encryptButton = view.findViewById(R.id.buttonEncrypt);
        encryptedText = view.findViewById(R.id.textViewEncrypted);
        encryptedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = encryptedText.getText().toString();
                copyTextToClipboard(textToCopy);
            }
        });
        know = view.findViewById(R.id.abt);
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                know.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='https://www.geeksforgeeks.org/vernam-cipher-in-cryptography/'> Vernam Cipher</a>";
                know.setText(Html.fromHtml(text));
            }
        });

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plaintext = plainText.getText().toString().toUpperCase();
                String key = keyText.getText().toString().toUpperCase();

                String encryptedMessage = encryptVernam(plaintext, key);
                encryptedText.setText(encryptedMessage);
            }
        });
        return view;
    }

    // Vernam cipher encryption method
    private String encryptVernam(String plaintext, String key) {
        if (plaintext.length() != key.length()) {
            encryptedText.setError("Enter the text and keyword of same length");
        }

        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            int encryptedChar = ((plaintext.charAt(i) - 'A') + (key.charAt(i) - 'A')) % 26 + 'A';
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
