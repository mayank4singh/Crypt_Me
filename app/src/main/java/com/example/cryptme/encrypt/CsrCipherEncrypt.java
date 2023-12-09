package com.example.cryptme.encrypt;

import static androidx.core.content.ContextCompat.getSystemService;

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
import android.widget.Toast;

import com.example.cryptme.R;

public class CsrCipherEncrypt extends Fragment {
  EditText text, key;
  TextView result, know;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_csr_cipher, container, false);
        Button btn = view.findViewById(R.id.enptbtn);
        know = view.findViewById(R.id.abt);
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                know.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href = 'https://www.geeksforgeeks.org/caesar-cipher-in-cryptography/'> Caesar Cipher </a>";
                know.setText(Html.fromHtml(text));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    encryptText(view);

            }
        });
        return view;
    }

    public void encryptText(View view) {

        text = getView().findViewById(R.id.inptmsg);
        key = getView().findViewById(R.id.inptkey);
        String str = key.getText().toString().trim();
        result = getView().findViewById(R.id.rslt);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = result.getText().toString();
                copyTextToClipboard(textToCopy);
            }
        });
        String plaintext = text.getText().toString().trim();

        if (plaintext.isEmpty()) {
            text.setError("Enter a text");
        } else if (str.isEmpty() || !str.matches("\\d+") || Integer.parseInt(str) >= 26) {
            key.setError("Enter a key and a number less than 26");
        } else {
            int shiftKey = Integer.parseInt(str);
            String ciphertext = encrypt(plaintext, shiftKey);
            result.setText(ciphertext);
        }
    }

    private String encrypt(String plaintext, int shiftKey) {
        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ciphertext.append((char) ((ch - base + shiftKey) % 26 + base));
            } else {
                // Preserve non-alphabetic characters
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();

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