package com.reviling.androidfilament3d.Retrofit_Call;



import com.reviling.androidfilament3d.utils.Constant;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET(Constant.Profile)
    Call<ResponseBody> GetProfiledata();


}
