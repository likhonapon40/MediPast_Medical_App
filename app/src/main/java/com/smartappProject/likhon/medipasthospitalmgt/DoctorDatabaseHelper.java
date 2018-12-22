package com.smartappProject.likhon.medipasthospitalmgt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoctorDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DoctorDatabase";
    public static final int VERSION = 7;

    public static final String TABLE_NAME = "DoctorInfo";
    public static final String MEDICAL_HISTORY_TABLE_NAME = "MedicalHistoryInfo";
    public static final String DOCTOR_COLUMN_ID = "Id";
    public static final String DOCTOR_COLUMN_NAME = "Name";
    public static final String DOCTOR_COLUMN_DETAILS = "Details";
    public static final String DOCTOR_COLUMN_APPOINTMENT = "Appointment";
    public static final String DOCTOR_COLUMN_PHONE_NUMBER = "PhoneNumber";
    public static final String DOCTOR_COLUMN_EMAIL_ID = "EmailId";
    public static final String DOCTOR_COLUMN_PRESCRIPTION_IMAGE = "PrescriptionImage";

    public static final String tableCreateQuery = "create table " +TABLE_NAME+ "("+DOCTOR_COLUMN_ID+ " integer primary key," +DOCTOR_COLUMN_NAME+ " text," +DOCTOR_COLUMN_DETAILS+ " text," +DOCTOR_COLUMN_APPOINTMENT+ " text," +DOCTOR_COLUMN_PHONE_NUMBER+ " text," +DOCTOR_COLUMN_EMAIL_ID+ " text," +DOCTOR_COLUMN_PRESCRIPTION_IMAGE+ " text" + ")";

    public static final String medicalHistoryTableCreateQuery = "create table " +MEDICAL_HISTORY_TABLE_NAME+ "("+DOCTOR_COLUMN_ID+ " integer primary key," +DOCTOR_COLUMN_NAME+ " text," +DOCTOR_COLUMN_DETAILS+ " text," +DOCTOR_COLUMN_APPOINTMENT+ " text," +DOCTOR_COLUMN_PHONE_NUMBER+ " text," +DOCTOR_COLUMN_EMAIL_ID+ " text," +DOCTOR_COLUMN_PRESCRIPTION_IMAGE+ " text" + ")";

    public DoctorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableCreateQuery);
        db.execSQL(medicalHistoryTableCreateQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
