package com.krsolutions.upstack.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.krsolutions.upstack.BuildConfig;
import com.krsolutions.upstack.Database.Tag.Tag;
import com.krsolutions.upstack.Database.Tag.TagDao;
import com.krsolutions.upstack.R;
import com.krsolutions.upstack.Upstack;
import com.krsolutions.upstack.adapter.SelectedTagItemAdapter;
import com.krsolutions.upstack.adapter.TagItemAdapter;
import com.krsolutions.upstack.api.model.tagResponse;
import com.krsolutions.upstack.api.service.stackService;
import com.krsolutions.upstack.viewmodel.SelectedTagItem;
import com.krsolutions.upstack.viewmodel.TagItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserInterestAcitivity extends AppCompatActivity {

    private static final String TAG = "UserInterestAcitivity";
    RecyclerView listViewPopularTags;
    RecyclerView listViewSelectedTags;
    MaterialButton buttonSubmit;
    ProgressBar progressBar;
    SelectedTagItem selectedTagItem = new SelectedTagItem();
    String[] tags_string;
    Context context;
    TagDao tagDao;
    SharedPreferences sp;
    List<TagItem> popularTagsList=new ArrayList<>();
    Intent QuestionFeedIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interest);
        buttonSubmit=findViewById(R.id.submitButton);
        listViewPopularTags=findViewById(R.id.allTagsListView);
        progressBar = findViewById(R.id.progress_circular);
        context=getBaseContext();
        tagDao=((Upstack)getApplicationContext()).getAppDatabase().tagDAO();
        sp=getSharedPreferences(getPackageName(),MODE_PRIVATE);
        QuestionFeedIntent = new Intent(this,QuestionFeedActivity.class);
        if(sp.contains("tags_saved")){
            startActivity(QuestionFeedIntent);
            finish();
        }
        listViewSelectedTags=findViewById(R.id.selectedTagsListView);
        getPopularTags();
//        buttonSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: \nSelected Tags: "+selectedTagItem.getSelectedTags().size());
//                Toast.makeText(getApplicationContext(),"Number of Tags Selected: "+selectedTagItem.getSelectedTags().size(),Toast.LENGTH_SHORT).show();
//
//            }
//        });
        buttonSubmit.setOnClickListener(saveTags);
        buttonSubmit.setOnLongClickListener(readTags);
    }

    void getPopularTags(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.stackexchange.com/").addConverterFactory(GsonConverterFactory.create()).build();
        final stackService stackClient = retrofit.create(stackService.class);
        final Call<tagResponse> popularTags = stackClient.getPopularTags();
        popularTags.enqueue(new Callback<tagResponse>() {
            @Override
            public void onResponse(Call<tagResponse> call, Response<tagResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: \n"+response.body().getItems().get(0).getName());
                    for(int i=0;i<response.body().getItems().size();i++){
                        TagItem tagItem = new TagItem(response.body().getItems().get(i).getName(),false);
                        popularTagsList.add(tagItem);
                    }
                    SelectedTagItemAdapter selectedTagItemAdapter = new SelectedTagItemAdapter(selectedTagItem.getSelectedTags());
                    TagItemAdapter adapter = new TagItemAdapter(popularTagsList,getApplicationContext(),selectedTagItem,selectedTagItemAdapter);
                    listViewPopularTags.setAdapter(adapter);
                    listViewPopularTags.setLayoutManager(new GridLayoutManager(getApplicationContext(),GridLayoutManager.chooseSize(1,2,2)));
                    progressBar.setVisibility(View.GONE);

                    listViewSelectedTags.setAdapter(selectedTagItemAdapter);
                    listViewSelectedTags.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
                }else{
                    Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<tagResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Internet Not Found",Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener saveTags = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            deleteAsyncTask.execute();
            for(int i = 0 ; i < selectedTagItem.getSelectedTags().size() ; i ++ ){
                final Tag tag = new Tag();
                tag.setName(selectedTagItem.getSelectedTags().get(i).getName());
                AsyncTask<Void,Void,Void> writerAsync = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        tagDao.insertTag(tag);
                        Log.d(TAG, "run: Inserted Tag: "+tag);
                        return null;
                    }
                };
                writerAsync.execute();
                Toast.makeText(context,"Inserted Tag: "+tag,Toast.LENGTH_SHORT).show();
                sp.edit().putBoolean("tags_saved",true).commit();
                startActivity(QuestionFeedIntent);
                finish();
            }
        }
    };

    View.OnLongClickListener readTags = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            AsyncTask<Void,Void,Void> readerAsync =new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<Tag> tags  = tagDao.getAllTags();
                    Log.d(TAG, "run: \nSelected Tags: "+tags);
                    return null;
                }
            };
            readerAsync.execute();
            return true;
            }
    };
    AsyncTask<Void,Void,Void> deleteAsyncTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            tagDao.deleteAll();
            return null;
        }
    };
}
