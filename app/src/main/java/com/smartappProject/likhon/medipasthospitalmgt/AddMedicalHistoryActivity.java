package com.smartappProject.likhon.medipasthospitalmgt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class AddMedicalHistoryActivity extends AppCompatActivity {
    private ImageView prescriptionIv;
    private Button takePrescriptionImageBtn, addMedicalHistoryInformationBtn;
    private EditText medicalDoctorNameEt, medicalDoctorDetailsEt;
    private TextView medicalDoctorAppointmentTv;
    private static final int REQUEST_CODE_FOR_CAMERA = 1;
    private String imageFromCamera;
    private MedicalHistoryDatabaseManager medicalHistoryDatabaseManager;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical_history);

        initialization();
        onClick();
    }

    private void onClick() {
        takePrescriptionImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) !=null){
                    startActivityForResult(intent, REQUEST_CODE_FOR_CAMERA);
                }
            }
        });

        addMedicalHistoryInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = medicalDoctorNameEt.getText().toString();
                String details = medicalDoctorDetailsEt.getText().toString();
                String appointment = medicalDoctorAppointmentTv.getText().toString();


                if(name.equals("") | details.equals("") | appointment.equals("")){
                    Toast.makeText(AddMedicalHistoryActivity.this, "Please fill up the all above filed!", Toast.LENGTH_SHORT).show();

                }else{
                    medicalDoctorNameEt.setText("");
                    medicalDoctorDetailsEt.setText("");
                    medicalDoctorAppointmentTv.setText("Choose an appointment date");
                    prescriptionIv.setImageBitmap(null);

                    Doctor doctor = new Doctor(name,details, appointment, imageFromCamera);
                    boolean insert = medicalHistoryDatabaseManager.insertMedicalHistory(doctor);

                    if(insert){
                        Toast.makeText(AddMedicalHistoryActivity.this, "Added Information", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddMedicalHistoryActivity.this, "Added Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        medicalDoctorAppointmentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMedicalHistoryActivity.this,
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
                medicalDoctorAppointmentTv.setText(date);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOR_CAMERA && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageFromCamera = encodeImage(bitmap, Bitmap.CompressFormat.JPEG, 70);
            prescriptionIv.setImageBitmap(bitmap);
        }
    }

    public static String encodeImage(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality,byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
    }

    private void initialization() {
        prescriptionIv = findViewById(R.id.addMedicalPrescriptionIvId);

        takePrescriptionImageBtn = findViewById(R.id.takePrescriptionImageBtnId);
        addMedicalHistoryInformationBtn = findViewById(R.id.addMedicalHistoryInformationBtnId);

        medicalDoctorNameEt = findViewById(R.id.addMedicalDoctorNameEtId);
        medicalDoctorDetailsEt = findViewById(R.id.addMedicalDoctorDetailsEtId);
        medicalDoctorAppointmentTv = findViewById(R.id.addMedicalDoctorAppointmentTvId);

        medicalHistoryDatabaseManager = new MedicalHistoryDatabaseManager(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_medical_history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.addMedicalHistoryHomeMenuId){
            startActivity(new Intent(AddMedicalHistoryActivity.this, WelcomeScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }else if(itemId == R.id.addMedicalHistoryViewInformationMenuId){
            startActivity(new Intent(AddMedicalHistoryActivity.this, ViewMedicalHistoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        return super.onOptionsItemSelected(item);
    }
}
