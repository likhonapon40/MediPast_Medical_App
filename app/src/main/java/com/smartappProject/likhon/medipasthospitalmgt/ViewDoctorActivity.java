package com.smartappProject.likhon.medipasthospitalmgt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class ViewDoctorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DoctorDatabaseManager doctorDatabaseManager;
    private AddDoctorAdapter addDoctorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor);

        initialization();
    }

    private void initialization() {
        recyclerView = findViewById(R.id.addDoctorRecyclerViewId);
        doctorDatabaseManager = new DoctorDatabaseManager(this);
        List<Doctor> doctorList = doctorDatabaseManager.getAllDoctorInformation();
        addDoctorAdapter = new AddDoctorAdapter(this, doctorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(addDoctorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_doctor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.viewDoctorHomeMenuId){
            startActivity(new Intent(ViewDoctorActivity.this, WelcomeScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        return super.onOptionsItemSelected(item);
    }
}
