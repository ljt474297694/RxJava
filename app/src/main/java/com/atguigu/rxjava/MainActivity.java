package com.atguigu.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Integer[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 调度器类型
     * Schedulers.computation()	用于计算任务，如事件循环或和回调处理，不要用于IO操作(IO操作请使用Schedulers.io())；默认线程数等于处理器的数量
     * <p>
     * Schedulers.from(executor)	使用指定的Executor作为调度器
     * <p>
     * Schedulers.immediate()	在当前线程立即开始执行任务
     * <p>
     * Schedulers.io()	用于IO密集型任务，如异步阻塞IO操作，这个调度器的线程池会根据需要增长；对于普通的计算任务，请使用Schedulers.computation()；
     * Schedulers.io() 默认是一个CachedThreadScheduler，很像一个有线程缓存的新线程调度器
     * <p>
     * Schedulers.newThread()	为每个任务创建一个新线程
     * <p>
     * Schedulers.trampoline()	当其它排队的任务完成后，在当前线程排队开始执行
     * <p>
     * AndroidSchedulers.mainThread()	此调度器为RxAndroid特有，顾名思义，运行在Android UI线程上
     * <p>
     * 运算符
     * Map：最常用且最实用的操作符之一，将对象转换成另一个对象发射出去，应用范围非常广，如数据的转换，数据的预处理等。
     * 例一：数据类型转换，改变最终的接收的数据类型。假设传入本地图片路径，根据路径获取图片的Bitmap。
     * <p>
     * FlatMap：和Map很像但又有所区别，Map只是转换发射的数据类型，而FlatMap可以将原始Observable转换成另一个Observable。
     * 循环拆解 内部遍历
     * <p>
     * Filter：过滤，通过谓词判断的项才会被发射
     * <p>
     * Distinct:去掉重复的项，比较好理解：
     * <p>
     * Take：发射前n项数据
     * <p>
     * Buffer：缓存，可以设置缓存大小，缓存满后，以list的方式将数据发送出去
     */

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                createObservable();
//                testRxJava();
                break;
            case R.id.bt2:
                testRxJava();
                break;
            case R.id.bt3:
                break;
            case R.id.bt4:
                break;
            case R.id.bt5:
                break;
            case R.id.bt6:
                break;
        }
    }

    private void createObservable() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext("123");
                    e.onNext("234");
                    e.onNext("345");
                    e.onComplete();
                }
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String value) {
                Log.e("TAG", "MainActivity onNext()" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "MainActivity onError()" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "MainActivity onComplete()");
            }
        });
    }

    private void testRxJava() {
        Observable.fromArray(a).subscribeOn(Schedulers.io()).flatMap(new Function<Integer[], ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer[] integers) throws Exception {
                return Observable.fromArray(integers);
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer % 2 == 0;
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "map" + integer;
            }
        }).buffer(2).take(1).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(List<String> value) {
                Log.e("TAG", "MainActivity onNext()" + value.toString());
                Toast.makeText(MainActivity.this, value.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "MainActivity onError()" + e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }


}
