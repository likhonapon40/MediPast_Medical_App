package com.smartappProject.likhon.medipasthospitalmgt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.DOCTOR_COLUMN_APPOINTMENT;
import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.DOCTOR_COLUMN_DETAILS;
import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.DOCTOR_COLUMN_EMAIL_ID;
import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.DOCTOR_COLUMN_ID;
import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.DOCTOR_COLUMN_NAME;
import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.DOCTOR_COLUMN_PHONE_NUMBER;
import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.DOCTOR_COLUMN_PRESCRIPTION_IMAGE;
import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.MEDICAL_HISTORY_TABLE_NAME;

public class MedicalHistoryDatabaseManager {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private DoctorDatabaseHelper doctorDatabaseHelper;

    public MedicalHistoryDatabaseManager(Context context) {
        this.context = context;
        doctorDatabaseHelper = new DoctorDatabaseHelper(context);
    }

    public boolean insertMedicalHistory(Doctor doctor){
        sqLiteDatabase = doctorDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_NAME, doctor.getDoctorName());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_DETAILS, doctor.getDoctorDetails());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_APPOINTMENT, doctor.getDoctorAppointment());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_PHONE_NUMBER, doctor.getDoctorPhoneNumber());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_EMAIL_ID, doctor.getDoctorEmailId());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_PRESCRIPTION_IMAGE, doctor.getPrescriptionImage());

        long insertRow = sqLiteDatabase.insert(MEDICAL_HISTORY_TABLE_NAME, null, contentValues);
        if (insertRow>0){
            sqLiteDatabase.close();
            return true;

        }else {
            return false;

        }
    }

    public List<Doctor> getAllMedicalHistoryInformation(){
        sqLiteDatabase = doctorDatabaseHelper.getReadableDatabase();
        List<Doctor> doctorList = new ArrayList<>();
        String query = "select * from "+MEDICAL_HISTORY_TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DOCTOR_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_NAME));
                String details = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_DETAILS));
                String appointment = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_APPOINTMENT));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_PHONE_NUMBER));
                String emailId = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_EMAIL_ID));
                String prescriptionImage = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_PRESCRIPTION_IMAGE));

                Doctor doctor = new Doctor(id, name, details, appointment, phoneNumber, emailId, prescriptionImage);
                doctorList.add(doctor);
            }while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return doctorList;
    }
}
