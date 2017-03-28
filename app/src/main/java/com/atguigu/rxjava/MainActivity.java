package com.atguigu.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_loadimage)
    Button btnLoadimage;
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.activity_net_image)
    LinearLayout activityNetImage;
    Integer[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 调度器类型	
     * Schedulers.computation()	用于计算任务，如事件循环或和回调处理，不要用于IO操作(IO操作请使用Schedulers.io())；默认线程数等于处理器的数量
     *
     * Schedulers.from(executor)	使用指定的Executor作为调度器
     *
     * Schedulers.immediate()	在当前线程立即开始执行任务
     *
     * Schedulers.io()	用于IO密集型任务，如异步阻塞IO操作，这个调度器的线程池会根据需要增长；对于普通的计算任务，请使用Schedulers.computation()；
     * Schedulers.io() 默认是一个CachedThreadScheduler，很像一个有线程缓存的新线程调度器
     *
     * Schedulers.newThread()	为每个任务创建一个新线程
     *
     * Schedulers.trampoline()	当其它排队的任务完成后，在当前线程排队开始执行
     *
     * AndroidSchedulers.mainThread()	此调度器为RxAndroid特有，顾名思义，运行在Android UI线程上
     *
     * 运算符
     * Map：最常用且最实用的操作符之一，将对象转换成另一个对象发射出去，应用范围非常广，如数据的转换，数据的预处理等。
     * 例一：数据类型转换，改变最终的接收的数据类型。假设传入本地图片路径，根据路径获取图片的Bitmap。
     *
     * FlatMap：和Map很像但又有所区别，Map只是转换发射的数据类型，而FlatMap可以将原始Observable转换成另一个Observable。
     * 循环拆解 内部遍历
     *
     * Filter：过滤，通过谓词判断的项才会被发射
     *
     * Distinct:去掉重复的项，比较好理解：
     *
     * Take：发射前n项数据
     *
     * Buffer：缓存，可以设置缓存大小，缓存满后，以list的方式将数据发送出去
     */
    @OnClick(R.id.btn_loadimage)
    public void onClick() {
        Observable.from(a).subscribeOn(Schedulers.io()).flatMap(new Func1<Integer[], Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer[] ints) {
                return Observable.from(ints);
            }
        }).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {

                return "map" + integer;
            }
        }).buffer(2).take(1).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> list) {
                Log.e("TAG", "MainActivity call()" + list.toString());
                Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
            }
        });


//        Observable
//                .create(new Observable.OnSubscribe<Bitmap>() {
//                    @Override
//                    public void call(final Subscriber<? super Bitmap> subscriber) {
//                        new OkHttpClient().newCall(new Request.Builder().url("http://img6.5sing.kgimg.com/force/T19Yh3BmET1RXrhCrK_188_188.jpg").build()).enqueue(new Callback() {
//                            @Override
//                            public void onFailure(Call call, IOException e) {
//                                subscriber.onError(e);
//                            }
//
//                            @Override
//                            public void onResponse(Call call, Response response) throws IOException {
//                                byte[] bytes = response.body().bytes();
//                                if (bytes != null) {
//                                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                                    subscriber.onNext(bitmap);
//                                }
//                            }
//                        });
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap b) {
//                        iv.setImageBitmap(b);
//                    }
//                });

    }
}
