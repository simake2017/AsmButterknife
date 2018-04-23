package com.yn.asmbutterknife;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yn.annotations.BindView;
import com.yn.annotations.ViewInject;

@ViewInject(ViewInject.ACTIVITY)
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    private TextView tv;
    @BindView(R.id.rc)
    private RecyclerView rc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        this.tv = findViewById(R.id.tv);
//        this.rc = findViewById(R.id.rc);
        String lookBeforeInClassFileSeeWhatHasInjected = "TestActivity.class";
    }
}
