package sk.ukf.zamestnancidb.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private String message;
    private T data;
    private String dateTime;

    public ApiResponse(T data, String message, String dateTime) {
        this.message = message;
        this.data = data;
        this.dateTime = dateTime;
    }

    public static <T> ApiResponse<T> success(T data, String dateTime) {
        return new ApiResponse<>(data, null, dateTime);
    }

    public static <T> ApiResponse<T> success(T data, String message, String dateTime) {
        return new ApiResponse<>(data, message, dateTime);
    }

    public static <T> ApiResponse<T> error(String message, String dateTime) {
        return new ApiResponse<>(null, message, dateTime);
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

