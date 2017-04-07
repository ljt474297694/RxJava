package com.atguigu.retrofitandrxjava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class RetrofitRxjavaActivity extends RxAppCompatActivity {
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
        setContentView(R.layout.activity_retrofit_rxjava);
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
//                .baseUrl(baseUrl)//baseUrl 请求根连接 此处为 "http://47.93.118.241:8081/"
//                .addConverterFactory(GsonConverterFactory.create()) //Gson解析工厂 自动解析Bean
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava回调工厂 表示支持返回Observable
//                .build();
//
//        RequestServes requestServes = retrofit.create(RequestServes.class);

        //调用工具类 创建业务接口实例
        RequestServes retrofitServes = new RetrofitUtils<RequestServes>()
                .createRetrofitServes(baseUrl, RequestServes.class);


        retrofitServes.login(username, password, "123123123")
                .subscribeOn(Schedulers.io())
                //Rxlifecycle的使用 关联Activity的声明周期
                //表示当 Activity Destroy的时候停止发射数据
                .compose(this.<User>bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        tvContent.setText(user.toString());
                    }
                });
    }

    //业务接口
    public interface RequestServes {
        //使用@Query("字段名")   Retrofit会自动拼接字段 发送请求
        //拼接后续连接
        @FormUrlEncoded
        @POST("android/user/login")
        Observable<User> login(@Field("username") String username,
                               @Field("password") String password,
                               @Field("phone") String phone);
    }

}
