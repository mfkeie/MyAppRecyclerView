package ru.uia.example.myapprecyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.uia.example.myapprecyclerview.mock.MockAdapter;
import ru.uia.example.myapprecyclerview.mock.MockGenerator;

public class RecyclerFragment extends Fragment {

    private RecyclerView mRecycler;

    private final MockAdapter mockAdapter = new MockAdapter();

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecycler = view.findViewById(R.id.recycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mockAdapter);
        mockAdapter.addData(MockGenerator.generate(20));
    }
}
