package com.cos.phoneapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

//어댑터와 리사이클러 뷰와 연결(databinding 사용금지)
public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {
    private static final String TAG = "PhoneAdapter";
    private MainActivity mainActivity;
    private List<Phone> phones;

    public PhoneAdapter(MainActivity mainActivity,List<Phone> phones) {
        this.mainActivity = mainActivity;
        this.phones = phones;
    }
    public void setPhones(List<Phone> phones){
        this.phones = phones;
        notifyDataSetChanged();
    }
    public void addPhone(Phone phone){
        phones.add(phone);
        notifyDataSetChanged();
    }
    public Phone getPhone(int position){
        return phones.get(position);
    }
    public  void updatePhone(int position, Phone phone){
        phones.get(position).setId(phone.getId());
        phones.get(position).setName(phone.getName());
        phones.get(position).setTel(phone.getTel());
        notifyDataSetChanged();
    }
    public void deletePhone(int position){
        phones.remove(position);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout phoneItem;
        TextView tvName;
        TextView tvTel;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            Log.d(TAG, "ViewHolder: ");
            phoneItem = itemView.findViewById(R.id.layout_phone_item);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTel = itemView.findViewById(R.id.tv_tel);

            phoneItem.setOnClickListener(v -> {
                View dialogView = v.inflate(v.getContext(),R.layout.layout_add_phone,null);
                final EditText etName = dialogView.findViewById(R.id.et_name);
                final EditText etTel = dialogView.findViewById(R.id.et_tel);
                Phone phone = getPhone(getAdapterPosition());
                etName.setText(phone.getName());
                etTel.setText(phone.getTel());

                AlertDialog.Builder dlg = new AlertDialog.Builder(mainActivity);
                dlg.setTitle("연락처 수정");
                dlg.setView(dialogView);
                dlg.setPositiveButton("수정",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Long id = getPhone(getAdapterPosition()).getId();
                        mainActivity.updatePhone(id,new Phone(null,etName.getText().toString(),etTel.getText().toString()),getAdapterPosition());
                    }
                });
                dlg.setNegativeButton("삭제",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Long id = getPhone(getAdapterPosition()).getId();
                        mainActivity.deletePhone(id,getAdapterPosition());
                    }
                });
                dlg.show();
            });
        }

        void setItem(Phone phone){
            Log.d(TAG, "setItem: ");
            tvName.setText(phone.getName());
            tvTel.setText(phone.getTel());
        }
    }

    @NonNull
    @Override
    public PhoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setItem(phones.get(position));
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }


}
