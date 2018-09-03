package com.example.jun.travelreminder.helper;

import android.content.Context;
import android.widget.EditText;

public class ValidateHelper {

    public static boolean validate(Context context, EditText view, Type validate) {
        if (view.getText().toString().isEmpty()) {
            view.requestFocus();
            view.setError("Isi dulu datanya");
            return false;
        }
        return true;
    }

    public enum Type {
        EMPTY,
    }
}
