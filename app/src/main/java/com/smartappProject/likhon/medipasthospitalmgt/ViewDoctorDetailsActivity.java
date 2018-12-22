package com.smartappProject.likhon.medipasthospitalmgt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewDoctorDetailsActivity extends AppCompatActivity {
    private TextView detailsViewDoctorNameTv, detailsViewDoctorDetailsTv, detailsViewDoctorAppointmentTv, detailsViewDoctorPhoneNumberTv, detailsViewDoctorEmailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor_details);

        initialization();
    }

    private void initialization() {
        detailsViewDoctorNameTv = findViewById(R.id.detailsViewDoctorNameTvId);
        detailsViewDoctorDetailsTv = findViewById(R.id.detailsViewDoctorDetailsTvId);
        detailsViewDoctorAppointmentTv = findViewById(R.id.detailsViewDoctorAppointmentTvId);
        detailsViewDoctorPhoneNumberTv = findViewById(R.id.detailsViewDoctorPhoneNumberTvId);
        detailsViewDoctorEmailTv = findViewById(R.id.detailsViewDoctorEmailTvId);

        Intent receiveData = getIntent();
        List<Doctor> doctorList = new ArrayList<>();
        AddDoctorAdapter addDoctorAdapter = new AddDoctorAdapter(this, doctorList);
        String name = receiveData.getStringExtra("Name");
        String details = receiveData.getStringExtra("Details");
        String appointment = receiveData.getStringExtra("Appointment");
        String phoneNumber = receiveData.getStringExtra("PhoneNumber");
        String emailId = receiveData.getStringExtra("EmailId");

        detailsViewDoctorNameTv.setText(name);
        detailsViewDoctorDetailsTv.setText(details);
        detailsViewDoctorAppointmentTv.setText(appointment);
        detailsViewDoctorPhoneNumberTv.setText(phoneNumber);
        detailsViewDoctorEmailTv.setText(emailId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_doctor_details_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.detailsViewDoctorHomeMenuId){
            startActivity(new Intent(ViewDoctorDetailsActivity.this, WelcomeScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        return super.onOptionsItemSelected(item);
    }
}
