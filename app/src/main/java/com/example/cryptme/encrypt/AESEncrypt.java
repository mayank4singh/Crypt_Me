package com.example.cryptme.encrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cryptme.R;
import com.example.cryptme.SharedViewModel;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESEncrypt extends Fragment {


    private EditText inputText;
    private TextView encryptedText, know;
    private Button encryptButton;
    private SharedViewModel sharedViewModel;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_aes_cipher, container, false);
        inputText = view.findViewById(R.id.inptmsg);
        encryptedText = view.findViewById(R.id.rslt);
        know = view.findViewById(R.id.abt);
        know.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
              know.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href = 'https://www.geeksforgeeks.org/advanced-encryption-standard-aes/'> Advanced Encryption Standard (AES) </a>";
                know.setText(Html.fromHtml(text));
            }
        });

        encryptedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = encryptedText.getText().toString();
                copyTextToClipboard(textToCopy);
            }
        });
        encryptButton = view.findViewById(R.id.enptbtn);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                try {
                    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                    keyGenerator.init(256);
                    SecretKey secretKey =keyGenerator.generateKey();

                    sharedViewModel.setSecretKey(secretKey);

                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    byte[] encryptedbytes =cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedbytes);
                        encryptedText.setText(encryptedBase64);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
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
