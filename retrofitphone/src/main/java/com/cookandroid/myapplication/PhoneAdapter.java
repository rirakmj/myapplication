package com.cookandroid.myapplication;

import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {

    private List<Phone> phoneList;

    public PhoneAdapter(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvTel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel = itemView.findViewById(R.id.tvTel);
        }
    }

    // insert
    public void addItem(Phone phone) {
        phoneList.add(phone);
        notifyDataSetChanged(); // 새로고침
    }

    @NonNull
    @Override
    public PhoneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_list, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.MyViewHolder holder, int position) {
        Phone phone = phoneList.get(position);
        holder.tvName.setText(phone.getName());
        holder.tvTel.setText(phone.getTel());
    }

    @Override
    public int getItemCount() {

        return phoneList == null ? 0 : phoneList.size();
    }
}



//holder.itemView.setOnClickListener(new View.OnClickListener()

  //  {
    //    @Override
      //  public void onClick (View v){
        //View dialogView = v.inflate(v.getContext(), R.layout.layout_add_concat, ); // 다이얼로그 창에 값 출력
        //final EditText etName = dialogView.findViewById(R.id.etName);
        //final EditText etTel = dialogView.findViewById(R.id.etTel);
//
//                etName.setText(Phone.getName());
//                etTel.setText(Phone.getTel());
//
//                AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
//                dlg.setTitle("연락처 수정");
//                dlg.setView(dialogView);
//                dlg.setPositiveButton("Update", new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Phone phoneDto = new Phone();
//                        phoneDto.setName(etName.getText().toString());
//                        phoneDto.setTel(etTel.getText().toString());
//
//                        Log.d("update", "onClick: 등록 클릭시 값 확인" + phoneDto);
//
//                        PhoneService phoneService = Retrofit2Client.getInstance().getPhoneService();
//                        Call<Phone> call = phoneService.update(Phone.getId().phoneDto);
//
//                    }
//                })
//            }
//        });


