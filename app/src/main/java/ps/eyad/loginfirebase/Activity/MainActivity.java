package ps.eyad.loginfirebase.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ps.eyad.loginfirebase.Adapter.DataAdapter;
import ps.eyad.loginfirebase.Model.Data;
import ps.eyad.loginfirebase.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvMain;
    SharedPreferences sp ;
    SharedPreferences.Editor spEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();


        sp = getSharedPreferences("User", MODE_PRIVATE);
        spEditor = sp.edit();
        StringRequest request = new StringRequest(Request.Method.POST, "http://aaqsoftware.com/aaq_APIs/Auth/getUser.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    Log.e("data",object+"");
                    ArrayList<Data> data = new ArrayList<>();
                    JSONArray array = object.getJSONArray("data");
                    for (int i=0 ; i<array.length();i++){
                        Data data1 = (Data) array.get(i);
                        data.add(data1);
                    }
                    DataAdapter adapter = new DataAdapter(data);
                    mRvMain.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    mRvMain.setAdapter(adapter);
                    mRvMain.setHasFixedSize(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>map = new HashMap<>();
                map.put("appID","com.unitone.android.assignment");
                map.put("email",sp.getString("email",""));
                map.put("password",sp.getString("password",""));
                return map;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    private void findView() {

        mRvMain = findViewById(R.id.rv_main);
    }
}