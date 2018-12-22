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
import static com.smartappProject.likhon.medipasthospitalmgt.DoctorDatabaseHelper.TABLE_NAME;


public class DoctorDatabaseManager {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private DoctorDatabaseHelper doctorDatabaseHelper;

    public DoctorDatabaseManager(Context context) {
        this.context = context;
        doctorDatabaseHelper = new DoctorDatabaseHelper(context);
    }

    public boolean insertDoctor(Doctor doctor){
        sqLiteDatabase = doctorDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_NAME, doctor.getDoctorName());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_DETAILS, doctor.getDoctorDetails());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_APPOINTMENT, doctor.getDoctorAppointment());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_PHONE_NUMBER, doctor.getDoctorPhoneNumber());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_EMAIL_ID, doctor.getDoctorEmailId());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_PRESCRIPTION_IMAGE, doctor.getPrescriptionImage());

        long insertRow = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (insertRow>0){
            sqLiteDatabase.close();
            return true;

        }else {
            return false;

        }
    }

    public List<Doctor> getAllDoctorInformation(){
        sqLiteDatabase = doctorDatabaseHelper.getReadableDatabase();
        List<Doctor> doctorList = new ArrayList<>();
        String query = "select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DOCTOR_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_NAME));
                String details = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_DETAILS));
                String appointment = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_APPOINTMENT));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_PHONE_NUMBER));
                String emailId = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_EMAIL_ID));
//                String prescriptionImage = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_PRESCRIPTION_IMAGE));

                Doctor doctor = new Doctor(id, name, details, appointment, phoneNumber, emailId);
                doctorList.add(doctor);
            }while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return doctorList;
    }

    public Doctor getAllDoctorInformationById(int id){
        sqLiteDatabase = doctorDatabaseHelper.getReadableDatabase();
        Doctor doctor = null;
        String query = "select * from "+TABLE_NAME+" where id="+id;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int doctorId = cursor.getInt(cursor.getColumnIndex(DOCTOR_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_NAME));
                String details = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_DETAILS));
                String appointment = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_APPOINTMENT));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_PHONE_NUMBER));
                String emailId = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_EMAIL_ID));
//                String prescriptionImage = cursor.getString(cursor.getColumnIndex(DOCTOR_COLUMN_PRESCRIPTION_IMAGE));

                doctor = new Doctor(doctorId, name, details, appointment, phoneNumber, emailId);

            }while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return doctor;
    }

    public boolean updateDoctorInformation(Doctor doctor){
        sqLiteDatabase = doctorDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_NAME, doctor.getDoctorName());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_DETAILS, doctor.getDoctorDetails());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_APPOINTMENT, doctor.getDoctorAppointment());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_PHONE_NUMBER, doctor.getDoctorPhoneNumber());
        contentValues.put(DoctorDatabaseHelper.DOCTOR_COLUMN_EMAIL_ID, doctor.getDoctorEmailId());

        int updateRow = sqLiteDatabase.update(TABLE_NAME, contentValues, DOCTOR_COLUMN_ID+" = "+ doctor.getId(), null);
        if(updateRow>0){
            sqLiteDatabase.close();
            return true;
        }else{
            return false;
        }

    }
}
