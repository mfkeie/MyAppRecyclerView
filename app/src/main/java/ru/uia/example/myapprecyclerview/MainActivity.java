package ru.uia.example.myapprecyclerview;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.OnItemClickListener {

    //Добавить фрагмент с recyclerView
    //Добавить адаптер, холдер и генератор заглущечных данных
    //Добавить обновление данных и состояние ошибки
    //Добавить загрузку данных телефонной книги
    //Добавить обработку нажатий на элементы списка
    //Добавить декораторы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecyclerFragment.newInstance())
                    .commit();
    }

    //Обработка нажатия на элемент списка
    //Лучше всего реализовывать этот интерфейс в активити, для возможности легкого изменения реализации метода
    @Override
    public void onItemClick(String id) {
        //Для получения номера телефона, обратимся к контентпровайдеру напрямую, а не как со списком контактов
        //Создаем контент провайдер и обратившись к нему получаем курсор,
        //который хранит один столбец 'номер телефона'
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, /*откуда*/
                /*столбец номера телефонов*/  new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                /*условие выборки конкретного номера телефона по ид и типу номера телефона*/ ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " + ContactsContract.CommonDataKinds.Phone.TYPE + " = ? ",
                /*значения которые подставятся вместо вопросительных знаков: ид которое получили*/ new String[]{id, String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)},
                /*сортировка*/ null);

        if(cursor != null && cursor.moveToFirst()) {
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            cursor.close();

            //Создаем интент для запуска приложения звоноков
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+ number)));
        }

        Toast.makeText(this, "Clicked " + id, Toast.LENGTH_LONG).show();
    }
}
