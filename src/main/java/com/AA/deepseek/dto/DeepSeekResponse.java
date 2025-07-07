package com.AA.deepseek.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeepSeekResponse {
    private String id;
    private String model;
    private List<ResponseChoice> choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ResponseChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<ResponseChoice> choices) {
        this.choices = choices;
    }
}