package example.com.jsonparsing.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.jsonparsing.interfaces.ApiCallback;
import example.com.jsonparsing.utils.AppConstants;
import example.com.jsonparsing.R;
import example.com.jsonparsing.model.Movie;

import static example.com.jsonparsing.remote.ApiClient.getDatas;

public class MainActivity extends AppCompatActivity implements ApiCallback {
    //View to display names
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> namesList;
    private ApiCallback mApiCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        namesList = new ArrayList<>();
        mApiCallback = this;
        getDatas(mApiCallback);

    }

    /**
     * getting datas from api
     * if the api gives success response
     * @param response
     */
    @Override
    public void succsessCallback(List<Movie> response) {
        List<Movie> movies = response;
        for (int i = 0; i < movies.size(); i++) {
            // namesList is filled with title names from the movies object
            namesList.add(movies.get(i).getOriginalTitle());
        }
        /* setting datas into the adapter*/
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, namesList);
        /* setting adapter in the list*/
        listView.setAdapter(adapter);
    }

    /**
     * Showing a toast message for failre response
     */
    @Override
    public void failureCallback() {
        Toast.makeText(getApplicationContext(), "Sorry, no data available", Toast.LENGTH_LONG).show();
    }
}
