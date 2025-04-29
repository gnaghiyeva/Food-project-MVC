package org.example.food.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class APIResponse {
    private boolean success;
    private String message;
    private Object data;

    public APIResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public APIResponse(boolean success, String message, Object data){
        this.success = success;
        this.message = message;
        this.data = data;
    }


}
