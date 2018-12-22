package com.smartappProject.likhon.medipasthospitalmgt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class ViewMedicalHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AddMedicalAdapter addMedicalAdapter;
    private MedicalHistoryDatabaseManager medicalHistoryDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_history);

        initialization();
    }

    private void initialization() {
        recyclerView = findViewById(R.id.medicalRecyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicalHistoryDatabaseManager = new MedicalHistoryDatabaseManager(this);
        List<Doctor> doctorList = medicalHistoryDatabaseManager.getAllMedicalHistoryInformation();
        addMedicalAdapter = new AddMedicalAdapter(this, doctorList);
        recyclerView.setAdapter(addMedicalAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_medical_history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.viewMedicalHistoryHomeMenuId){
            startActivity(new Intent(ViewMedicalHistoryActivity.this, WelcomeScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        return super.onOptionsItemSelected(item);
    }
}
