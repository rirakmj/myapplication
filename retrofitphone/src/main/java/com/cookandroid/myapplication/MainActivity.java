package com.cookandroid.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhoneAdapter phoneAdapter;
    LinearLayoutManager manager;
    private FloatingActionButton floatingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 데이터 추가
        floatingBtn = findViewById(R.id.floatingBtn);
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addContact();
            }
        });

        // 전체 보기
        PhoneService phoneService = Retrofit2Client.getInstance().getPhoneService(); // 만들어진 PhoneService 객체를 반환해줌
        Call<List<Phone>> call = phoneService.findAll();

        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                List<Phone> phoneList = response.body();
                recyclerView = findViewById(R.id.recyclerview); // 받아온 값을 RecyclerView에 뿌리기
                manager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(manager);

                phoneAdapter = new PhoneAdapter(phoneList);
                recyclerView.setAdapter(phoneAdapter);
                Log.d("data", "onResponse: 응답 받은 데이터: " + phoneList);
            }

            @Override
            public void onFailure(Call<List<Phone>> call, Throwable t) {

            }
        });
    }

    // 추가하기
    private void addContact() {
        View dialogView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.layout_add_concat, null);

        final EditText etName = dialogView.findViewById(R.id.etName);
        final EditText etTel = dialogView.findViewById(R.id.etTel);

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("연락처 등록");
        dlg.setView(dialogView);
        dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Phone phoneDto = new Phone();
                phoneDto.setName(etName.getText().toString());
                phoneDto.setTel(etTel.getText().toString());

                Log.d("insert", "onClick: 등록 클릭시 값 확인" +phoneDto);

                PhoneService phoneService = Retrofit2Client.getInstance().getPhoneService();
                Call<Phone> call = phoneService.save(phoneDto);

                call.enqueue(new Callback<Phone>() {
                    @Override
                    public void onResponse(Call<Phone> call, Response<Phone> response) {
                        Log.d("insert response", "onResponse: phoneDto" +phoneDto);

                        phoneAdapter.addItem(response.body());
                    }

                    @Override
                    public void onFailure(Call<Phone> call, Throwable t) {

                    }

                });
            }
        });
        dlg.setNegativeButton("닫기", null);
        dlg.show();
    }
}


    // 수정
    //PhoneService phoneService = Retrofit2Client.getInstance().getPhoneService();
    //Call<Phone> call = phoneService.update(phone.getId(), phoneDto);
    //call.enqueue(new Callback<Phone>() {
      //  @Override
        //public void onResponse(Call<Phone> call, Response<Phone> response) {
          //  Log.d("body", "onResponse: phoneDto" + phoneDto);

            //phoneAdapter.addItem(response.body());
        //}

        //@Override
        //public void onFailure(Call<Phone> call, Throwable t) {

        //}

        // 삭제
        //dlg.setNegativeButton("Delete", new DialogInterface.OnClickListener(){
        //@Override
        //public void onClick(DialogInterface dialog, int which){
        //PhoneService phoneService = Retrofit2Client.getInstance().getPhoneService();
        //Call<Void> call = phoneService.deleteById(phone.getId());
        //call.enqueue(new Callback<Void>(){
          //  @Override
            //public void onResponse(Call<Void> call, Response<Void> response) {
              //  removeItem(position);
            //}
        //})}
        //}
    //})
//}