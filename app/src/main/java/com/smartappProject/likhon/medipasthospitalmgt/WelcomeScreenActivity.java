package com.smartappProject.likhon.medipasthospitalmgt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreenActivity extends AppCompatActivity {
    private Button wlcmAddDoctorBtn, wlcmViewDoctorBtn, wlcmAddMedicalHistoryBtn, wlcmViewMedicalHistoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        initialization();
        onClick();

    }

    private void onClick() {
        wlcmAddDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, AddDoctorActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        wlcmViewDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, ViewDoctorActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        wlcmAddMedicalHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, AddMedicalHistoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        wlcmViewMedicalHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, ViewMedicalHistoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    private void initialization() {
        wlcmAddDoctorBtn = findViewById(R.id.wlcmAddDoctorBtnId);
        wlcmViewDoctorBtn = findViewById(R.id.wlcmViewDoctorBtnId);
        wlcmAddMedicalHistoryBtn = findViewById(R.id.wlcmAddMedicalHistoryBtnId);
        wlcmViewMedicalHistoryBtn = findViewById(R.id.wlcmViewMedicalHistoryBtnId);
    }
}
