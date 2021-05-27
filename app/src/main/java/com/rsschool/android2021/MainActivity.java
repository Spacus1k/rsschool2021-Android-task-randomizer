package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements OnGeneratePressed,
        OnBackButtonPressed {

    private static final String SECOND_FRAGMENT_TAG = "SecondFragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment).commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment, SECOND_FRAGMENT_TAG).commit();
    }

    @Override
    public void onGeneratePressed(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void onBackButtonPressed(int previousResult) {
        openFirstFragment(previousResult);
    }

    @Override
    public void onBackPressed() {
        SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager()
                .findFragmentByTag(SecondFragment.SECOND_FRAGMENT_TAG);
        if (secondFragment != null) {
            openFirstFragment(secondFragment.getResult());
        } else {
            super.onBackPressed();
        }
    }
}
