package com.krsolutions.upstack.api.service;

import com.krsolutions.upstack.api.model.questionResponse;
import com.krsolutions.upstack.api.model.tagResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface stackService {
    @GET ("/2.2/tags?page=1&pagesize=100&order=desc&sort=popular&site=stackoverflow")  Call<tagResponse> getPopularTags();
    @GET("/2.2/questions?pagesize=99&order=desc&sort=activity&site=stackoverflow") Call<questionResponse> getQuestionFeed();
}
