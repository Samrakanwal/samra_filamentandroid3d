package com.reviling.androidfilament3d;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.reviling.androidfilament3d.Retrofit_Call.ApiInterface;
import com.reviling.androidfilament3d.singleton.RetrofitSingleton;
import com.reviling.samra.filamentandroid.MainActivity.R;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileScreen extends AppCompatActivity {


    public final int CAMERA_PERMISSTION_REQUEST = 115;

    ImageView btnBack;
    Button updatebtn;
    String USER;
    ListView listView;

    Bitmap photo;

    String currentPhotoPath;

    ImageView userpc, cm;

    private ApiInterface scalarService;

    TextView username,email,phoneno,dateofbirth,country,city,streetname,streetno,zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prifile);
        initialView();
        getdata();

    }


    @SuppressLint("WrongViewCast")
    private void initialView() {
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phneno);
        dateofbirth = findViewById(R.id.dateofbirth);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        streetname = findViewById(R.id.streetname);
        streetno = findViewById(R.id.streetaddress);
        zipcode = findViewById(R.id.zipcode);

        scalarService = RetrofitSingleton.getClient().create(ApiInterface.class);


    }

    public void getdata() {

        JSONObject params = new JSONObject();
        try {

            RequestBody body;
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(String.valueOf(params))).toString());
            Call<ResponseBody> stringCall = scalarService.GetProfiledata();
            stringCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {
                        try {

                            JSONObject json = new JSONObject();
                            JSONObject jsonone = json.getJSONObject("address");
                            String ausername = json.getString("username");
                            String aemail = json.getString("email");
                            String aphone_number = json.getString("phone_number");
                            String adate_of_birth = json.getString("date_of_birth");
                            String acountry = jsonone.getString("country");
                            String acity = jsonone.getString("city");
                            String astreet_name = jsonone.getString("street_name");
                            String astreet_address = jsonone.getString("street_address");
                            String azip_code = jsonone.getString("zip_code");

                            username.setText(ausername);
                            email.setText(aemail);
                            phoneno.setText(aphone_number);
                            dateofbirth.setText(adate_of_birth);
                            country.setText(acountry);
                            city.setText(acity);
                            streetname.setText(astreet_name);
                            streetno.setText(astreet_address);
                            zipcode.setText(azip_code);



                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            // setAdapter();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("TAG", t.toString());

                    Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

    }

}