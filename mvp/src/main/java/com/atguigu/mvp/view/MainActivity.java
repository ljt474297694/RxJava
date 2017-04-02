package com.atguigu.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mvp.R;
import com.atguigu.mvp.bean.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_user)
    TextView tvUser;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVeiw();
        initData();
    }
    private void initVeiw() {
        ButterKnife.bind(this);
    }

    private void initData() {
        User user = (User) getIntent().getSerializableExtra("user");
        tvUser.setText(user.toString());
    }
}
