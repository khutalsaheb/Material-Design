package com.sandesh.mytest;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class FullScreenDialog extends DialogFragment {

    AppCompatTextView txt_name, txt_mobile, txt_email, txt_message;
    private String name, mobile, email, message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);

        if (getArguments() != null) {
            name = getArguments().getString("name", "");
            mobile = getArguments().getString("mobile", "");
            email = getArguments().getString("email", "");
            message = getArguments().getString("message", "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_full_screen_dialog, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        toolbar.setNavigationOnClickListener(view1 -> cancelUpload());
        toolbar.setTitle("View Form Details");
        txt_name = view.findViewById(R.id.txt_name);
        txt_mobile = view.findViewById(R.id.txt_mobile);
        txt_email = view.findViewById(R.id.txt_email);
        txt_message = view.findViewById(R.id.txt_message);
        AppCompatImageView action_image_main = view.findViewById(R.id.action_image_main);
        Glide.with(this).asGif().load(R.drawable.signform).into(action_image_main);
        MaterialButton done = view.findViewById(R.id.done);
        done.setOnClickListener(view1 -> cancelUpload());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_name.setText(name);
        txt_mobile.setText(mobile);
        txt_email.setText(email);
        txt_message.setText(message);
    }

    private void cancelUpload() {
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }


}