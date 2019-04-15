package com.krsolutions.upstack;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.krsolutions.upstack.api.model.tagResponse;
import com.krsolutions.upstack.api.service.stackService;
import com.krsolutions.upstack.viewmodel.SelectedTagItem;
import com.krsolutions.upstack.viewmodel.TagItem;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

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
    String[] tags_string;
    List<SelectedTagItem> selected_Tags=new ArrayList<>();
    List<TagItem> popularTagsList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interest);
        buttonSubmit=findViewById(R.id.submitButton);
        listViewPopularTags=findViewById(R.id.allTagsListView);
        listViewSelectedTags=findViewById(R.id.selectedTagsListView);
        getPopularTags();

    }

    void getPopularTags(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.stackexchange.com/").addConverterFactory(GsonConverterFactory.create()).build();
        final stackService stackClient = retrofit.create(stackService.class);
        final Call<tagResponse> popularTags = stackClient.getPopularTags();
        popularTags.enqueue(new Callback<tagResponse>() {
            @Override
            public void onResponse(Call<tagResponse> call, Response<tagResponse> response) {
                if(response.isSuccessful()){
                    ItemAdapter itemAdapter = new ItemAdapter();
                    Log.d(TAG, "onResponse: \n"+response.body().getItems().get(0).getName());
                    for(int i=0;i<response.body().getItems().size();i++){
                        TagItem tagItem = new TagItem();
                        tagItem.setName(response.body().getItems().get(i).getName());
                        popularTagsList.add(tagItem);
                    }
//                    listViewPopularTags.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item_tag,R.id.text1,tags_string));
//                    listViewPopularTags.setDivider(null);
//                    listViewPopularTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            TextView text=view.findViewById(R.id.text1);
//                            if(selected_Tags==null){
//                                selected_Tags=new ArrayList<>();
//                            }if(selected_Tags.contains(text.getText().toString())){
//                                selected_Tags.remove(text.getText().toString());
//                                text.setTextColor(Color.parseColor("#000000"));
//                            }else {
//                                selected_Tags.add(text.getText().toString());
//                                text.setTextColor(getApplicationContext().getColor(R.color.colorPrimary));
//                            }
//                            Log.d(TAG, "onItemClick: \n Selected Tags: "+selected_Tags);
//                            listViewSelectedTags.setDivider(null);
//                            listViewSelectedTags.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item_tag_selected,R.id.selectedTag,selected_Tags));
//                        }
//                    })
                    itemAdapter.add(popularTagsList);
                    FastAdapter fastAdapter =FastAdapter.with(itemAdapter);
                    f
                    listViewPopularTags.setAdapter(fastAdapter);
                    listViewPopularTags.setLayoutManager(new GridLayoutManager(getApplicationContext(),GridLayoutManager.chooseSize(1,3,2)));
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
}
