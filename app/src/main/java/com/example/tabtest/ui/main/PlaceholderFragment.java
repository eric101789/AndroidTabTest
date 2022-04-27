package com.example.tabtest.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tabtest.R;
import com.example.tabtest.databinding.FragmentMainBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    final static String TAG = "MainActivity";

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentMainBinding binding;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);

        Log.d(TAG, "enter Fragment's newInstance()");

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        uiInit(root);

        int position = getArguments().getInt(ARG_SECTION_NUMBER);

        Log.d(TAG, "enter " + position + " Fragment's onCreateView()");

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        int position = getArguments().getInt(ARG_SECTION_NUMBER);
        Log.d(TAG, "enter " + position + " Fragment's onStart()");
    }

    @Override
    public void onStop() {
        int position = getArguments().getInt(ARG_SECTION_NUMBER);
        Log.d(TAG, "enter " + position + " Fragment's onStop()");
        super.onStop();
    }

    private void uiInit(View root) {
        final TextView textView = binding.sectionLabel;
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        int position = getArguments().getInt(ARG_SECTION_NUMBER);

        switch (position) {
            case 1:
                root.setBackgroundColor(Color.RED);
                break;
            case 2:
                root.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                root.setBackgroundColor(Color.BLUE);
                break;
            case 4:
                root.setBackgroundColor(Color.GRAY);
                break;
        }

    }

//    private void uiInit(View root) {
//        final TextView textView = binding.sectionLabel;
//        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}