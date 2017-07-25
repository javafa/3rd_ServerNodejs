package com.veryworks.android.servernodejs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class MainActivity extends AppCompatActivity {

    private Button btnWrite;
    private RecyclerView recyclerView;
    private List<Bbs> data;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        data = new ArrayList<>();
        adapter = new RecyclerAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        btnWrite = (Button) findViewById(R.id.btnWrite);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void loader() {
        // 1. 레트로핏 생성
        Retrofit client = new Retrofit.Builder()
                .baseUrl(MyServer.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        // 2. 서비스 연결
        MyServer myServer = client.create(MyServer.class);

        // 3. 서비스의 특정 함수 호출 -> Observable 생성
        Observable<Bbs> observable = myServer.read();

        // 4. subscribe 등록
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                data -> {
                    // 1. 데이터를 꺼내고

                    // 2. 데이터를 아답터에 세팅하고

                    // 3. 아답터 갱신
                }
            );
    }

    interface MyServer {
        public static final String SERVER = "http://192.168.10.240/";

        @GET("bbs")
        public Observable<Bbs> read();

        @POST("bbs")
        public void write(Bbs bbs);

        @PUT("bbs")
        public void update(Bbs bbs);

        @DELETE("bbs")
        public void delete(Bbs bbs);
    }
}
