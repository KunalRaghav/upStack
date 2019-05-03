package com.krsolutions.upstack.Activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.krsolutions.upstack.Fragment.FeedFragment;
import com.krsolutions.upstack.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionFeedActivity extends AppCompatActivity {
    FrameLayout fragHolder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_feed);
        fragHolder = findViewById(R.id.fragment_host);
        FeedFragment feedFragment = new FeedFragment(this);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_host,feedFragment,"feedFrag").commit();
    }
}
