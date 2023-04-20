package com.example.chatgptapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText textfield;
    List<Message> messages = new ArrayList<Message>();
    GPTAdapter adapter;
    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textfield = findViewById(R.id.GPTInput);
        RequestBuilder.setupRestClient();
        messages.add(new Message("Ask any question!"));
        adapter = new GPTAdapter(messages);
        rec = findViewById(R.id.message_scroller);
        rec.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        rec.setHasFixedSize(true);
        rec.setAdapter(adapter);

    }

    public void sendMessage(View view) {

        String message = textfield.getText().toString();
        textfield.setText("");
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        adapter.messages.add(new Message(message));
        adapter.notifyItemInserted(adapter.messages.size()-1);

        Call<GPTResponse> call = RequestBuilder.getRestClient().sendMessage(new GPTRequest(messages));
        call.enqueue(new Callback<GPTResponse>() {
            @Override
            public void onResponse(Call<GPTResponse> call, Response<GPTResponse> response) {
                if(response.isSuccessful())
                {
                    Log.i("GPT RESPONSE",response.body().getChoices().get(0).getMessage().getContent());
                    adapter.messages.add(response.body().getChoices().get(0).getMessage());
                    adapter.notifyItemInserted(adapter.messages.size()-1);
                }
            }

            @Override
            public void onFailure(Call<GPTResponse> call, Throwable t) {

            }
        });
    }
}