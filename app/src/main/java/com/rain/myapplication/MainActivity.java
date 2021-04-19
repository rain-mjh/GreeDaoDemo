package com.rain.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rain.myapplication.entity.Meizi;
import com.rain.myapplication.utils.MeiziDaoUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    Button insert,all_insert,checkmult,update,delete,all_delete;

    MeiziDaoUtils mMeiziDaoUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mMeiziDaoUtils=new MeiziDaoUtils(this);
    }

    private void initView() {
        insert=findViewById(R.id.insert);
        all_insert=findViewById(R.id.all_insert);
        checkmult=findViewById(R.id.checkmult);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        all_delete=findViewById(R.id.all_delete);


        checkmult.setOnClickListener(this);
        insert.setOnClickListener(this);
        all_insert.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        all_delete.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                mMeiziDaoUtils.insertMeizi(new Meizi(null, "Google",
                        "http://7xi8d6.48096_n.jpg"));
                break;

            case R.id.checkmult:
                List<Meizi> meiziList1 = mMeiziDaoUtils.queryAllMeizi();
                for (Meizi meizi2 : meiziList1) {
                    Log.i(TAG, meizi2.toString());
                }
                break;

            case R.id.update:
                Meizi meizi = new Meizi();
                meizi.set_id(1L);
                meizi.setUrl("http://baidu.com");
                mMeiziDaoUtils.updateMeizi(meizi);
                break;

            case R.id.delete:
                deleteMeizi();
                break;

            case R.id.all_delete:
                mMeiziDaoUtils.deleteAll();
                break;


        }
    }
    public void deleteMeizi(){
        Meizi meizi=new Meizi();
        meizi.set_id(1L);
        mMeiziDaoUtils.deleteMeizi(meizi);
    }
}