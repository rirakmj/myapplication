package com.cookandroid.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<Person> personList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (MainActivity.this, RecyclerView.VERTICAL, false); // 객체 생성하기
        recyclerView.setLayoutManager(linearLayoutManager);

        PersonAdapter personAdapter = new PersonAdapter(personList); // 어댑터에 들어있는 값을 뿌려줘야 함
        recyclerView.setAdapter(personAdapter);

        PersonService personService = Retrofit2Client.getInstance().getPersonService();
        Call<List<Person>> call = personService.findAll();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                List<Person> personList = response.body();
                personAdapter.listItem(personList);

                Log.d("data", "onResponse: 응답 받은 데이터: " + personList);
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {

            }
        });


         // 데이터 강제 주입
//        for (int i = 0; i < 20; i++) {
//            personAdapter.addItem(new Person("홍길동" + i, "010-1111-1111"));
//        }

        // 리스너 인터페이스를 외부로 빼내어 토스트 메세지 띄우기
        personAdapter.setListener(new PersonListener() {
            @Override
            public void onItemClick(int position) {
                Person person = personAdapter.getItem(position);
                Toast.makeText(MainActivity.this, person.getName() + " 선택됨",
                        Toast.LENGTH_SHORT).show();

                EditText etName = findViewById(R.id.etName);
                EditText etTel = findViewById(R.id.etTel);
                etName.setText(person.getName());
                etTel.setText(person.getTel());
            }
        });

        // 전화번호 출력 롱클릭
        personAdapter.setOnLongItemClickListener(new PersonAdapter.OnLongItemClickListener() {
            @Override
            public void onLongItemClick(int pos) {
                Person person = personAdapter.getItem(pos);
                Toast.makeText(MainActivity.this, person.getTel() + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                personAdapter.removeItem(pos);
            }
        });

        Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etName = findViewById(R.id.etName);
                EditText etTel = findViewById(R.id.etTel);
                Person personDto = new Person(etName.getText().toString(), etTel.getText().toString());

                PersonService personService = Retrofit2Client.getInstance().getPersonService();
                Call<Person> call = personService.save(personDto);

                call.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        personAdapter.addItem(response.body());
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {

                    }
                });
            }
        });

    }
}