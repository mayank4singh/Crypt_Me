package com.example.cryptme.decrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cryptme.R;


public class CsrCipherDecrypt extends Fragment {
    public CsrCipherDecrypt() {
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_csr_cipher_decrypt, container, false);

        Button btn = view.findViewById(R.id.decrptResult);
        TextView know = view.findViewById(R.id.abt);
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

                DecryptText(view);
            }
        });
        return view;
    }

    private void DecryptText(View view) {
        EditText text = getView().findViewById(R.id.dmsg);
        EditText key = getView().findViewById(R.id.decrptkey);
        TextView result = getView().findViewById(R.id.decryptrslt);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = result.getText().toString();
                copyTextToClipboard(textToCopy);
            }
        });

        String plaintext = text.getText().toString();
        int plainkey = Integer.parseInt(key.getText().toString());

        String decrytptedText = decrypt(plaintext, plainkey);
        result.setText(decrytptedText);
    }

    private String decrypt(String text, int shift) {
        if (TextUtils.isEmpty(text)) return "";

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            if (Character.isLetter(current)) {
                char base = Character.isLowerCase(current) ? 'a' : 'A';
                char shifted = (char) (((current - base - shift + 26) % 26) + base);
                result.append(shifted);
            } else {
                result.append(current);
            }
        }
        return result.toString();
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