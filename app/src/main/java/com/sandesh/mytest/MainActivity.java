package com.sandesh.mytest;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText editName, edit_mobile, edit_email, edit_message;
    private CardView card_mobile, card_email, card_message;
    private MaterialButton btnName, btnMobile, btnEmail, btnSubmit;
    private String name, mobile, email, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnName = findViewById(R.id.btnName);
        btnMobile = findViewById(R.id.btnMobile);
        btnEmail = findViewById(R.id.btnEmail);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnName.setOnClickListener(this);
        btnMobile.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        editName = findViewById(R.id.editName);
        edit_mobile = findViewById(R.id.edit_mobile);
        edit_email = findViewById(R.id.edit_email);
        edit_message = findViewById(R.id.edit_message);

        card_mobile = findViewById(R.id.card_mobile);
        card_email = findViewById(R.id.card_email);
        card_message = findViewById(R.id.card_message);
        AppCompatImageView action_image_main = findViewById(R.id.action_image_main);
        Glide.with(this).asGif().load(R.drawable.signform).into(action_image_main);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnName:
                name = Objects.requireNonNull(editName.getText()).toString();
                if (TextUtils.isEmpty(name)) {
                    editName.setError(getString(R.string.enter_name));
                    editName.requestFocus();
                } else {
                    Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    btnName.startAnimation(slideDown);
                    btnName.setVisibility(View.GONE);
                    card_mobile.startAnimation(slideUp);
                    card_mobile.setVisibility(View.VISIBLE);
                    edit_mobile.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.showSoftInput(edit_mobile, InputMethodManager.SHOW_IMPLICIT);
                    }
                }
                break;
            case R.id.btnMobile:
                mobile = Objects.requireNonNull(edit_mobile.getText()).toString();
                if (TextUtils.isEmpty(mobile)) {
                    edit_mobile.setError(getString(R.string.enter_mobile));
                    edit_mobile.requestFocus();
                } else {
                    Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    btnMobile.startAnimation(slideDown);
                    btnMobile.setVisibility(View.GONE);
                    card_email.startAnimation(slideUp);
                    card_email.setVisibility(View.VISIBLE);
                    edit_email.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.showSoftInput(edit_email, InputMethodManager.SHOW_IMPLICIT);
                    }
                }
                break;
            case R.id.btnEmail:
                email = Objects.requireNonNull(edit_email.getText()).toString();
                if (TextUtils.isEmpty(email)) {
                    edit_email.setError(getString(R.string.enter_email));
                    edit_email.requestFocus();
                } else {
                    Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    btnEmail.startAnimation(slideDown);
                    btnEmail.setVisibility(View.GONE);
                    card_message.startAnimation(slideUp);
                    card_message.setVisibility(View.VISIBLE);
                    edit_message.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.showSoftInput(edit_message, InputMethodManager.SHOW_IMPLICIT);
                    }
                }
                break;
            case R.id.btnSubmit:
                message = Objects.requireNonNull(edit_message.getText()).toString();
                if (TextUtils.isEmpty(message)) {
                    edit_message.setError(getString(R.string.enter_message));
                    edit_message.requestFocus();
                } else {
                    registerUser(name, mobile, email, message);
                    showCustomDialog();
                }
                break;
        }
    }

    private void registerUser(final String name, final String mobile, final String email, final String message) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://instapreps.com/api/enrollnow",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("fullname", name);
                params.put("mobileno", mobile);
                params.put("emailid", email);
                params.put("message", message);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        MaterialButton buttonOk = dialogView.findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(view -> showEditDialog());
        AppCompatImageView action_image = dialogView.findViewById(R.id.action_image);

        Glide.with(this).asGif().load(R.drawable.gif20).into(action_image);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showEditDialog() {

        FullScreenDialog dialog = new FullScreenDialog();

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("mobile", mobile);
        bundle.putString("message", message);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "TAG");

    }
}
