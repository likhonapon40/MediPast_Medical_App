package com.smartappProject.likhon.medipasthospitalmgt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AddMedicalAdapter extends RecyclerView.Adapter<AddMedicalAdapter.AddMedicalViewHolder> {
    private Context context;
    List<Doctor> doctorList;

    public AddMedicalAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public AddMedicalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.model_medical_details_design, null);
        return new AddMedicalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddMedicalViewHolder addMedicalViewHolder, int i) {
        final Doctor getItemId = doctorList.get(i);
        addMedicalViewHolder.medicalDoctorPrescriptionIv.setImageBitmap(decodeImage(getItemId.getPrescriptionImage()));
        addMedicalViewHolder.medicalDoctorNameTv.setText(getItemId.getDoctorName());
        addMedicalViewHolder.medicalDoctorDetailsTv.setText(getItemId.getDoctorDetails());
        addMedicalViewHolder.medicalDoctorAppointmentTv.setText(getItemId.getDoctorAppointment());
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class AddMedicalViewHolder extends RecyclerView.ViewHolder{
        private TextView medicalDoctorNameTv, medicalDoctorDetailsTv, medicalDoctorAppointmentTv;
        private ImageView medicalDoctorPrescriptionIv;

        public AddMedicalViewHolder(@NonNull View itemView) {
            super(itemView);
            medicalDoctorNameTv = itemView.findViewById(R.id.recyclerViewMedicalDoctorNameTvId);
            medicalDoctorDetailsTv = itemView.findViewById(R.id.recyclerViewMedicalDoctorDetailsTvId);
            medicalDoctorAppointmentTv = itemView.findViewById(R.id.recyclerViewMedicalDoctorAppointmentTvId);
            medicalDoctorPrescriptionIv = itemView.findViewById(R.id.recyclerViewPrescriptionIvId);
        }
    }

    public static Bitmap decodeImage(String input){
        byte[] decodeByte = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }
}
