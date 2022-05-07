package com.example.userinput;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    CalendarView calendarView;
    EditText mssvET, nameET, cccdET, phoneET, mailET, dateET,homeET, addressET;
    RadioGroup nganhHoc;
    RadioButton nganh1RB, nganh2RB;
    CheckBox cCB, javaCB, pythonCB;
    CheckBox agreeCB;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        calendarView = findViewById(R.id.calendarView);
//        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
//            dateET.setText(new StringBuilder().append("").append(dayOfMonth).append("/").append(month).append("/").append(year).toString());
//            view.setVisibility(View.GONE);
//        });
        mssvET = findViewById(R.id.mssvEditText);
        nameET = findViewById(R.id.nameEditText);
        cccdET = findViewById(R.id.cccdEditText);
        phoneET = findViewById(R.id.phoneEditText);
        mailET = findViewById(R.id.mailEditText);
        dateET = findViewById(R.id.dateEditText);

        homeET = findViewById(R.id.homeEditText);
        addressET = findViewById(R.id.addressEditText);
        nganhHoc = findViewById(R.id.radioGroup2);
        nganh1RB = findViewById(R.id.radioButton1);
        nganh2RB = findViewById(R.id.radioButton2);
        cCB = findViewById(R.id.cCheckBox);
        javaCB = findViewById(R.id.javaCheckBox);
        pythonCB = findViewById(R.id.pythonCheckBox);
        agreeCB = findViewById(R.id.agreeCheckBox);
        findViewById(R.id.submit_btn).setOnClickListener(this);
        findViewById(R.id.selectDateBtn).setOnClickListener(v -> {
            Intent intent = new Intent(this, Calendar.class);
            startActivityForResult(intent, 123);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                String date = data.getStringExtra("Date");
                dateET.setText(date);
            } else if (resultCode == Activity.RESULT_CANCELED) {

            }

        }
    }

    @Override
    public void onClick(View v) {
        if (!agreeCB.isChecked()) {
            Toast toast = Toast.makeText(this, "Xác nhận điều khoản", Toast.LENGTH_LONG);
            toast.show();
            agreeCB.requestFocus();
        } else {
            if (checkEditText(mssvET, "mã số sinh viên") && checkEditText(nameET, "họ tên")
            && checkEditText(cccdET, "CCCD") && checkEditText(phoneET, "số điện thoại")
            && checkEditText(mailET, "email")) {
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("thongTin", thongTin());
                startActivity(intent);
            }

        }
    }

    private boolean checkEditText(EditText editText, String message) {
        if (editText != null) {
            if (editText.getText().length() == 0) {
                Toast.makeText(this, "Cần nhập " + message, Toast.LENGTH_SHORT).show();
                editText.requestFocus();
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private String thongTin() {
        StringBuilder result = new StringBuilder();
        result.append("MSSV: " + mssvET.getText() + "\n" +
                "Họ tên: " + nameET.getText() + "\n" +
                "Số CCCD: " + cccdET.getText() + "\n" +
                "Số điện thoại: " + phoneET.getText() + "\n" +
                "Email: " + mailET.getText() + "\n");
        Editable date = dateET.getText();
        Editable home = homeET.getText();
        Editable address = addressET.getText();
        if (date.length() != 0) result.append("Ngày sinh: " + date + "\n");
        if (home.length() != 0) result.append("Quê quán: " + home + "\n");
        if (address.length() != 0) result.append("Nơi ở hiện tại: " + address + "\n");
        int idNganh = nganhHoc.getCheckedRadioButtonId();
        if (idNganh != -1){
            if (idNganh == nganh1RB.getId()) result.append("Ngành học: " + nganh1RB.getText() + "\n");
            else if (idNganh == nganh2RB.getId()) result.append("Ngành học: " + nganh2RB.getText() + "\n");
        }
        result.append("NNLT: ");
        if (cCB.isChecked()) result.append(cCB.getText() + "\n");
        else if (cCB.isChecked()) result.append(cCB.getText() + "\n");
        else if (cCB.isChecked()) result.append(cCB.getText() + "\n");
        else result.delete(result.length() - 6, result.length());


        return result.toString();
    }

}