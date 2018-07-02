package ru.uia.example.myapprecyclerview;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import ru.uia.example.myapprecyclerview.mock.MockAdapter;
import ru.uia.example.myapprecyclerview.mock.MockGenerator;

public class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView mRecycler;
    private View mErrorView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Random random = new Random();

    private ContactsAdapter.OnItemClickListener mListener;

    //private final MockAdapter mockAdapter = new MockAdapter();
    private final ContactsAdapter mContactsAdapter = new ContactsAdapter();

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ContactsAdapter.OnItemClickListener) {
            mListener = (ContactsAdapter.OnItemClickListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecycler = view.findViewById(R.id.recycler);
        mSwipeRefreshLayout = view.findViewById(R.id.refresher);
        //Связываем реализацию OnRefreshListener с нашим layout-ом
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mErrorView = view.findViewById(R.id.error_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mContactsAdapter);
        //mockAdapter.addData(MockGenerator.generate(20));

        mContactsAdapter.setListener(mListener);
    }

    @Override
    public void onRefresh() {
       /* mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                int count  = random.nextInt(4);

                if(count == 0) {
                    showError();
                } else {
                    showData(count);
                }


                //Прячем кругляшку
                if(mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 2000);*/
       getLoaderManager().restartLoader(0, null, this);
    }

   /* private void showData(int count) {
        mockAdapter.addData(MockGenerator.generate(5), true);
        mErrorView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);

    }

    private void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);

    }*/

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(getActivity(),
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                null, null, ContactsContract.Contacts._ID
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mContactsAdapter.swapCursor(data);
        if(mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}
