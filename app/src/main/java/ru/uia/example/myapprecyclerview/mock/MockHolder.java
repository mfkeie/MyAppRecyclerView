package ru.uia.example.myapprecyclerview.mock;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.uia.example.myapprecyclerview.R;

public class MockHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mValue;

    public MockHolder(View itemView) {
        super(itemView);

        mName = itemView.findViewById(R.id.tv_name);
        mValue = itemView.findViewById(R.id.tv_value);
    }

    public void bind(Mock mock) {
        mName.setText(mock.getName());
        mValue.setText(mock.getValue());
    }
}
