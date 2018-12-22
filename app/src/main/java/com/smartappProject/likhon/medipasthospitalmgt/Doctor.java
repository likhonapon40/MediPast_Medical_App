package com.smartappProject.likhon.medipasthospitalmgt;

public class Doctor {
    private int id;
    private String doctorName;
    private String doctorDetails;
    private String doctorAppointment;
    private String doctorPhoneNumber;
    private String doctorEmailId;
    private String prescriptionImage;


    public Doctor(int id, String doctorName, String doctorDetails, String doctorAppointment, String doctorPhoneNumber, String doctorEmailId, String prescriptionImage) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.doctorAppointment = doctorAppointment;
        this.doctorPhoneNumber = doctorPhoneNumber;
        this.doctorEmailId = doctorEmailId;
        this.prescriptionImage = prescriptionImage;
    }

    public Doctor(String doctorName, String doctorDetails, String doctorAppointment, String doctorPhoneNumber, String doctorEmailId, String prescriptionImage) {
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.doctorAppointment = doctorAppointment;
        this.doctorPhoneNumber = doctorPhoneNumber;
        this.doctorEmailId = doctorEmailId;
        this.prescriptionImage = prescriptionImage;
    }

    public Doctor(String doctorName, String doctorDetails, String doctorAppointment, String doctorPhoneNumber, String doctorEmailId) {
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.doctorAppointment = doctorAppointment;
        this.doctorPhoneNumber = doctorPhoneNumber;
        this.doctorEmailId = doctorEmailId;
    }

    public Doctor(String doctorName, String doctorDetails, String doctorAppointment, String prescriptionImage) {
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.doctorAppointment = doctorAppointment;
        this.prescriptionImage = prescriptionImage;
    }

    public Doctor(int id, String doctorName, String doctorDetails, String doctorAppointment, String doctorPhoneNumber, String doctorEmailId) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.doctorAppointment = doctorAppointment;
        this.doctorPhoneNumber = doctorPhoneNumber;
        this.doctorEmailId = doctorEmailId;
    }

    public int getId() {
        return id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorDetails() {
        return doctorDetails;
    }

    public String getDoctorAppointment() {
        return doctorAppointment;
    }

    public String getDoctorPhoneNumber() {
        return doctorPhoneNumber;
    }

    public String getDoctorEmailId() {
        return doctorEmailId;
    }

    public String getPrescriptionImage() {
        return prescriptionImage;
    }
}
