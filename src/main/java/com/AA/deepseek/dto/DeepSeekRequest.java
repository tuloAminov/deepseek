package com.AA.deepseek.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeepSeekRequest {
    private String model = "deepseek-chat"; // Пример модели
    private List<ChatMessage> messages; // Обязательное поле!
    private int max_tokens = 2048;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    public static class ChatMessage {
        private String role; // "user" или "system"
        private String content;

        // Конструкторы
        public ChatMessage(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}