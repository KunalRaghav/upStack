package com.krsolutions.upstack.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.krsolutions.upstack.R;
import com.krsolutions.upstack.adapter.QuestionFeedAdapter;
import com.krsolutions.upstack.api.model.questionResponse;
import com.krsolutions.upstack.api.service.stackService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedFragment extends Fragment {
    private static final String TAG = "FeedFragment";
    RecyclerView QuestionFeedRecycler;
    QuestionFeedAdapter adapter;
    MaterialButton SortByButton;
    List<questionResponse.questionJson> Questions;
    ProgressBar progressBar;
    Context Activity_Context;

    public FeedFragment(Context activity_Context) {
        Activity_Context = activity_Context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar=view.findViewById(R.id.progress_circular);
        QuestionFeedRecycler=view.findViewById(R.id.feed_recycler);
        SortByButton=view.findViewById(R.id.sort_by_button);
        QuestionFeedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        QuestionFeedRecycler.setItemAnimator(new DefaultItemAnimator());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.stackexchange.com/").addConverterFactory(GsonConverterFactory.create()).build();
        final stackService stackClient = retrofit.create(stackService.class);
        Call<questionResponse> questionFeed = stackClient.getQuestionFeed();
        questionFeed.enqueue(new Callback<questionResponse>() {
            @Override
            public void onResponse(Call<questionResponse> call, Response<questionResponse> response) {
                if(response.isSuccessful()){
                    Questions=response.body().getItems();
                    adapter=new QuestionFeedAdapter(Questions,Activity_Context);
                    QuestionFeedRecycler.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onResponse: Questions:\n"+response.body().getItems().size());
                    for(int i=0;i<response.body().getItems().size();i++){
                        Log.d(TAG, "onResponse: Question "+(i+1)+": "+response.body().getItems().get(i).getAnswer_count());
                    }
                }
            }

            @Override
            public void onFailure(Call<questionResponse> call, Throwable t) {

            }
        });
        SortByButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(),SortByButton);
                popup.getMenu().add("Old");
                popup.getMenu().add("Popular");
                popup.getMenu().add("Controversial");
                popup.show();
            }
        });
    }
}
