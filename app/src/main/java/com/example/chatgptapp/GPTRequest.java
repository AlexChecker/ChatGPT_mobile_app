
package com.example.chatgptapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GPTRequest {

    public GPTRequest( List<Message> messages)
    {
        this.model= "gpt-3.5-turbo";
        this.messages= messages;
    }

    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("messages")
    @Expose
    private List<Message> messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
