package com.example.chatgptapp;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {
    @POST("/v1/chat/completions")
    @Headers({"Content-Type: application/json","Authorization: Bearer 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"})
    Call<GPTResponse> sendMessage(@Body GPTRequest body);



}


