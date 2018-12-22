package com.smartappProject.likhon.medipasthospitalmgt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddDoctorActivity extends AppCompatActivity {
    private EditText doctorNameEt, doctorDetailsEt, doctorPhoneNumberEt, doctorEmailEt;
    private TextView doctorAppointmentTv;
    private Button addDoctorInformationBtn, updateDoctorInformationBtn;
    private DoctorDatabaseManager doctorDatabaseManager;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private int doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        initialization();
        onClick();

    }

    private void onClick() {
        addDoctorInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = doctorNameEt.getText().toString();
                String details = doctorDetailsEt.getText().toString();
                String appointment = doctorAppointmentTv.getText().toString();
                String phoneNumber = doctorPhoneNumberEt.getText().toString();
                String email = doctorEmailEt.getText().toString();

                if (name.equals("") | details.equals("") | appointment.equals("") | phoneNumber.equals("") | email.equals("")){
                    Toast.makeText(AddDoctorActivity.this, "Please fill up the all above filed!", Toast.LENGTH_SHORT).show();

                }else {
                    doctorNameEt.setText("");
                    doctorDetailsEt.setText("");
                    doctorAppointmentTv.setText("Enter an appointment date");
                    doctorPhoneNumberEt.setText("");
                    doctorPhoneNumberEt.setText("");
                    doctorEmailEt.setText("");

                    Doctor doctor = new Doctor(name, details, appointment, phoneNumber, email);
                    boolean insert = doctorDatabaseManager.insertDoctor(doctor);
                    if (insert){
                        Toast.makeText(AddDoctorActivity.this, "Added Information", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(AddDoctorActivity.this, "Added Failed", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        updateDoctorInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = doctorNameEt.getText().toString();
                String details = doctorDetailsEt.getText().toString();
                String appointment = doctorAppointmentTv.getText().toString();
                String phoneNumber = doctorPhoneNumberEt.getText().toString();
                String email = doctorEmailEt.getText().toString();

                Doctor doctor = new Doctor(doctorId, name, details, appointment, phoneNumber, email);
                boolean updateInfrormation = doctorDatabaseManager.updateDoctorInformation(doctor);
                if(updateInfrormation){
                    Toast.makeText(AddDoctorActivity.this, "Updated Information", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddDoctorActivity.this, "Updated Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        doctorAppointmentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddDoctorActivity.this,
                        R.style.AddedTheme,
                        onDateSetListener, year, month, dayOfMonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                Log.d("ShowDate", "onDateSet: dd/mm/yy: "+ dayOfMonth + "." + month + "." + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                doctorAppointmentTv.setText(date);
            }
        };
    }

    private void initialization() {
        doctorNameEt = findViewById(R.id.addDoctorNameEtId);
        doctorDetailsEt = findViewById(R.id.addDoctorDetailsEtId);
        doctorAppointmentTv = findViewById(R.id.addDoctorAppointmentTvId);
        doctorPhoneNumberEt = findViewById(R.id.addDoctorPhoneNumberEtId);
        doctorEmailEt = findViewById(R.id.addDoctorEmailEtId);

        addDoctorInformationBtn = findViewById(R.id.addInformationBtnId);
        updateDoctorInformationBtn = findViewById(R.id.updateInformationBtnId);

        doctorDatabaseManager = new DoctorDatabaseManager(this);
        doctorId = getIntent().getIntExtra("id", 0);
        if(doctorId>0){
            Doctor doctor = doctorDatabaseManager.getAllDoctorInformationById(doctorId);
            doctorNameEt.setText(doctor.getDoctorName());
            doctorDetailsEt.setText(doctor.getDoctorDetails());
            doctorAppointmentTv.setText(doctor.getDoctorAppointment());
            doctorPhoneNumberEt.setText(doctor.getDoctorPhoneNumber());
            doctorEmailEt.setText(doctor.getDoctorEmailId());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_doctor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId==R.id.addDoctorHomeMenuId){
            startActivity(new Intent(AddDoctorActivity.this, WelcomeScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        }else if (itemId==R.id.viewInformationMenuId){
            startActivity(new Intent(AddDoctorActivity.this, ViewDoctorActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        }

        return super.onOptionsItemSelected(item);
    }
}
