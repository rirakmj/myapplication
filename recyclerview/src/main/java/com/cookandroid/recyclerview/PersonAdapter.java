package com.cookandroid.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    // 어댑터 내부에 인터페이스
    public interface OnLongItemClickListener {
        void onLongItemClick(int pos);
    }

    private OnLongItemClickListener onLongItemClickListener;

    public void setOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

    public void listItem(List<Person> personList){
        this.personList = personList;
        notifyDataSetChanged();
    }

    // 외부 인터페이스 리스너
    private PersonListener listener;

    public void setListener(PersonListener listener) {
        this.listener = listener;
    }

    private List<Person> personList;

    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvTel;
        private EditText etName;
        private EditText etTel;

        public MyViewHolder(@NonNull View itemView, final PersonListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel = itemView.findViewById(R.id.tvTel);
            etName = itemView.findViewById(R.id.etName);
            etTel = itemView.findViewById(R.id.etTel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();
                    onLongItemClickListener.onLongItemClick(pos);
                    return true;
                }
            });
        }
    }

    // 메서드 생성
    public void addItem(Person person) {
        personList.add(person);
        notifyDataSetChanged();
    }

    public Person getItem(int position) {
        Person person = personList.get(position);
        return person;
    }

    public void removeItem(int position) {
        personList.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personitem, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view, listener);
        return new PersonAdapter.MyViewHolder(view, listener); // listener도 같이 전달
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.tvName.setText(person.getName());
        holder.tvTel.setText(person.getTel());

    }

    @Override
    public int getItemCount() {
        return personList == null ? 0 : personList.size();
    }
}

