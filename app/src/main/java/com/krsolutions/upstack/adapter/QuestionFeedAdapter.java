package com.krsolutions.upstack.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krsolutions.upstack.R;
import com.krsolutions.upstack.api.model.questionResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QuestionFeedAdapter extends RecyclerView.Adapter<QuestionFeedAdapter.ViewHolder> {

    List<questionResponse.questionJson> questions;
    Context context;
    private static final String TAG = "QuestionFeedAdapter";

    public QuestionFeedAdapter(List<questionResponse.questionJson> questions, Context parent) {
        this.questions = questions;
        this.context=parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.question_feed_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        questionResponse.questionJson questionJson = (questionResponse.questionJson) questions.get(position);
        if(Build.VERSION.SDK_INT >= 24){
            holder.questionText.setText(Html.fromHtml((String) questionJson.getTitle(),1).toString());
        }else {
            holder.questionText.setText(Html.fromHtml((String) questionJson.getTitle()).toString());
        }
        Log.d(TAG, "onBindViewHolder: \n"+questions.get(position).getOwner().getDisplay_name()+"\n"+
                questionJson.getCreation_date().toString()+"\n"+questionJson.getAnswer_count().toString()+"\n"+
                questionJson.getView_count().toString());
        String d = questionJson.getCreation_date().toString();
        Log.d(TAG, "onBindViewHolder: date "+d);
        Date date =new Date(Long.valueOf(d)*1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm");
        holder.questionPoster.setText(questions.get(position).getOwner().getDisplay_name());
        holder.questionPostedDate.setText(dateFormat.format(date));
        holder.questionAnswerCount.setText(get_ValueString(Integer.valueOf(questionJson.getAnswer_count().toString())));
        holder.questionViewCount.setText(get_ValueString(Integer.valueOf(questionJson.getView_count().toString())));


    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        TextView questionPoster;
        TextView questionPostedDate;
        TextView questionViewCount;
        TextView questionAnswerCount;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            questionText=itemView.findViewById(R.id.feed_item_question_text);
            questionPoster=itemView.findViewById(R.id.feed_item_question_user_text);
            questionPostedDate=itemView.findViewById(R.id.feed_item_question_date_text);
            questionViewCount=itemView.findViewById(R.id.feed_item_question_view_text);
            questionAnswerCount=itemView.findViewById(R.id.feed_item_question_answer_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int index =getAdapterPosition();
                String url = questions.get(index).getLink();
                Intent browser_intent =new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                context.startActivity(browser_intent);
                    Log.d(TAG, "onClick: clicked");
                    Toast.makeText(v.getContext(),"clicked",Toast.LENGTH_LONG).show();
                }
            });
            Log.d(TAG, "ViewHolder: set click listener");
            
        }
    }
    String get_ValueString(int value){
        String s;
        if(value>1000000){
            s=(String.valueOf(value/1000000.0).substring(0,3))+"m";
        }
        else if(value>1000){
            s=(String.valueOf(value/1000.0).substring(0,3))+"k";
        }else{
            s=String.valueOf(value);
        }
        return s;
    }
}
