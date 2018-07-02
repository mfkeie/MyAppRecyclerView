package ru.uia.example.myapprecyclerview.mock;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.uia.example.myapprecyclerview.R;

public class MockAdapter extends RecyclerView.Adapter<MockHolder> {

    private final List<Mock> mMockList = new ArrayList<>();

    @NonNull
    @Override
    public MockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_mock, parent, false);
        return new MockHolder(view);
    }

    //bind - привязка
    //holder - держатель
    @Override
    public void onBindViewHolder(MockHolder holder, int position) {
        holder.bind(mMockList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMockList.size();
    }

    public void addData(List<Mock> mocks, boolean refresh) {
        //Перед добавлением новых данных, очищаем старые
        if(refresh)
            mMockList.clear();
        //Добавляем новые данные
        mMockList.addAll(mocks);
        //извещаем о изменении данных
        notifyDataSetChanged();
    }
}
