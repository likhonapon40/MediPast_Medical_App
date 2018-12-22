package com.smartappProject.likhon.medipasthospitalmgt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AddDoctorAdapter extends RecyclerView.Adapter<AddDoctorAdapter.DoctorViewHolder> {
    private Context context;
    List<Doctor> doctorList;

    public AddDoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.model_doctor_details_design, null);
        return new DoctorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoctorViewHolder doctorViewHolder, final int i) {
        final Doctor getItemId = doctorList.get(i);

        doctorViewHolder.addDoctorNameTv.setText(getItemId.getDoctorName());
        doctorViewHolder.addDoctorDetailsTv.setText(getItemId.getDoctorDetails());
        doctorViewHolder.addDoctorPhoneNumberTv.setText(getItemId.getDoctorPhoneNumber());

        doctorViewHolder.doctorDetailsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewDoctorDetailsActivity.class);
                intent.putExtra("Name", getItemId.getDoctorName());
                intent.putExtra("Details", getItemId.getDoctorDetails());
                intent.putExtra("Appointment", getItemId.getDoctorAppointment());
                intent.putExtra("PhoneNumber", getItemId.getDoctorPhoneNumber());
                intent.putExtra("EmailId", getItemId.getDoctorEmailId());
                context.startActivity(intent);
            }
        });

        doctorViewHolder.doctorDetailsCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int doctorId = doctorList.get(i).getId();
                context.startActivity(new Intent(context, AddDoctorActivity.class).putExtra("id", doctorId).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder{
        private TextView addDoctorNameTv, addDoctorDetailsTv, addDoctorPhoneNumberTv;
        private CardView doctorDetailsCardView;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            addDoctorNameTv = itemView.findViewById(R.id.recyclerViewDoctorNameTvId);
            addDoctorDetailsTv = itemView.findViewById(R.id.recyclerViewDoctorDetailsTvId);
            addDoctorPhoneNumberTv = itemView.findViewById(R.id.recyclerViewDoctorPhoneNumberTvId);
            doctorDetailsCardView = itemView.findViewById(R.id.doctorDetailsCardViewId);
        }
    }
}
