package com.atguigu.retrofitandrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RetrofitActivity extends AppCompatActivity {

    @Bind(R.id.et1)
    EditText et1;
    @Bind(R.id.et2)
    EditText et2;
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.tv_content)
    TextView tvContent;
    private String baseUrl = "http://47.93.118.241:8081/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn1)
    public void onClick() {
        login();
    }

    /**
     * 账号 123 密码 123 电话123123123 可以登录
     */
    public void login() {
        String username = et1.getText().toString().trim();
        String password = et2.getText().toString().trim();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl) //baseUrl 请求根连接 此处为 "http://47.93.118.241:8081/"
//                .addConverterFactory(GsonConverterFactory.create()) //Gson解析工厂 自动解析Bean
//                .build();
//
//        retrofit.create(RequestServes.class) //此处得到业务接口实例     RequestServes requestServes = retrofit.create(RequestServes.class);


        //调用工具类 创建业务接口实例
        RequestServes retrofitServes = new RetrofitUtils<RequestServes>()
                .createRetrofitServes(baseUrl, RequestServes.class);

        retrofitServes.login(username, password, "123123123") //调用业务接口方法 得到Call
                .enqueue(new Callback<User>() { //开启异步请求
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        //response.body() 直接得到解析Bean对象  此处为User
                        tvContent.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
    }

    //业务接口
    public interface RequestServes {
        //使用@Query("字段名")   Retrofit会自动拼接字段 发送请求
        //拼接后续连接
        @POST("android/user/login")
        Call<User> login(@Query("username") String username,
                         @Query("password") String password,
                         @Query("phone") String phone);
    }
}