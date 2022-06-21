package com.android.appfruit.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.appfruit.MainActivity;
import com.android.appfruit.R;
import com.android.appfruit.adapter.CartAdapter;
import com.android.appfruit.dto.CheckOutDto;
import com.android.appfruit.entity.ShoppingCart;
import com.android.appfruit.service.CartService;
import com.android.appfruit.util.RetrofitGenerator;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import retrofit2.Response;

public class CheckOutFragment extends Fragment {

    private View view;
    private Context currentContext;
    private CartService cartService;
    private String token = null;
    private TextInputEditText etName, etAddress, etPhone, etNote;
    private String name, address, phone, note;

    public CheckOutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentContext = container.getContext();
        view = inflater.inflate(R.layout.fragment_check_out, container, false);
        AppCompatButton submitCheckOut = view.findViewById(R.id.button_check_out);
        AppCompatButton continueShopping = view.findViewById(R.id.button_redirect);
        etName = view.findViewById(R.id.textInputEditTextName);
        etAddress = view.findViewById(R.id.textInputEditTextAddress);
        etPhone = view.findViewById(R.id.textInputEditTextPhone);
        etNote = view.findViewById(R.id.textInputEditTextNote);

        submitCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                address = etAddress.getText().toString();
                phone = etPhone.getText().toString();
                note = etNote.getText().toString();



                CheckOutDto checkOutDto = new CheckOutDto();
                checkOutDto.setShipName(name);
                checkOutDto.setShipAddress(address);
                checkOutDto.setShipPhone(phone);
                checkOutDto.setShipNote(note);

                if (validateName() && validateAddress() && validatePhone()) {
                    try {
                        cartService.checkOut(checkOutDto).execute();
                        Toast.makeText(view.getContext(), "Submit Success", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onButtonShowPopupWindowClick(view);
                }
            }
        });
        continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, MainActivity.productFragment, ProductFragment.class.getName())
                        .commit();
            }
        });
        config();
        return view;
    }
    private void config() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SharedPreferences settings = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = settings.getString("token", "");
        String refreshToken = settings.getString("refreshToken", "");
        Log.d("token", token);
        Log.d("refreshToken", refreshToken);
        if (cartService == null) {
            cartService = RetrofitGenerator.createService(CartService.class,token);
        }
    }

    //validate
    private boolean validateName() {

        if (name.isEmpty()) {
            etName.setError("Xin vui lòng nhập tên");
            etName.requestFocus();
            return false;
        } else {
            etName.setError(null);
            return true;
        }
    }

    private boolean validateAddress() {

        if (address.isEmpty()) {
            etAddress.setError("Xin vui lòng nhập địa chỉ");
            etAddress.requestFocus();
            return false;
        } else {
            etAddress.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {

        if (phone.matches("^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$")) {
            return true;
        } else if (phone.isEmpty()) {
            etPhone.setError("Xin vui lòng nhập số điện thoại");
            etPhone.requestFocus();
            return false;
        }
        etPhone.setError("Xin hãy nhập đúng mẫu số điện thoại");
        return false;
    }

    //popup
    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                currentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // Add shadow
        popupWindow.setElevation(20);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}