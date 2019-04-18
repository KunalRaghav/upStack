package com.krsolutions.upstack.adapter;

import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krsolutions.upstack.R;
import com.krsolutions.upstack.api.model.questionResponse;

import java.util.List;

public class QuestionFeedAdapter extends RecyclerView.Adapter<QuestionFeedAdapter.ViewHolder> {

    List<questionResponse.questionJson> questions;
    private static final String TAG = "QuestionFeedAdapter";

    public QuestionFeedAdapter(List<questionResponse.questionJson> questions) {
        this.questions = questions;
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
        holder.questionPoster.setText(questions.get(position).getOwner().getDisplay_name());
        holder.questionPostedDate.setText(questionJson.getCreation_date().toString());
        holder.questionAnswerCount.setText(questionJson.getAnswer_count().toString());
        holder.questionViewCount.setText(questionJson.getView_count().toString());

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView questionText;
        TextView questionPoster;
        TextView questionPostedDate;
        TextView questionViewCount;
        TextView questionAnswerCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText=itemView.findViewById(R.id.feed_item_question_text);
            questionPoster=itemView.findViewById(R.id.feed_item_question_user_text);
            questionPostedDate=itemView.findViewById(R.id.feed_item_question_date_text);
            questionViewCount=itemView.findViewById(R.id.feed_item_question_view_text);
            questionAnswerCount=itemView.findViewById(R.id.feed_item_question_answer_text);
        }
    }
}
