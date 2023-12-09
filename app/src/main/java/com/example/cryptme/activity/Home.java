package com.example.cryptme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cryptme.Me;
import com.example.cryptme.decrypt.DecryptionFrag;
import com.example.cryptme.encrypt.EncryptionFrag;
import com.example.cryptme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    EncryptionFrag encryptionFrag = new EncryptionFrag();
    DecryptionFrag decryptionFrag = new DecryptionFrag();
    Me me = new Me();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

      bottomNavigationView = findViewById(R.id.bottomNavigation);

        float cornerRadius = getResources().getFloat(R.dimen.corner_radius);
        MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable)bottomNavigationView.getBackground();
        ShapeAppearanceModel shapeAppearanceModel = materialShapeDrawable.getShapeAppearanceModel().toBuilder().setTopLeftCorner(CornerFamily.ROUNDED,cornerRadius).setTopRightCorner(CornerFamily.ROUNDED,cornerRadius).build();
        materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,encryptionFrag).commit();
      bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              switch (item.getItemId()){
                  case R.id.encryption:
                      getSupportFragmentManager().beginTransaction().replace(R.id.container,encryptionFrag).commit();
                      return true;

                  case R.id.decryption:
                      getSupportFragmentManager().beginTransaction().replace(R.id.container,decryptionFrag).commit();
                      return true;

                  case R.id.About:
                      getSupportFragmentManager().beginTransaction().replace(R.id.container,me).commit();
                      return true;

              }
              return false;
          }
      });


    }

    }


