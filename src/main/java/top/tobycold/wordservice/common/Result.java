package top.tobycold.wordservice.common;

public class Result<T> {
    private int code;
    private String message;
    private T data;
    private Result() {
    }
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T>Result<T> success() {
        return new Result<>(1, "success", null);
    }
    public static <T>Result<T> success(T t) {
        return new Result<>(1, "success", t);
    }
    public static <T>Result<T> error() {
        return new Result<>(0, "success", null);
    }
    public static <T>Result<T> error(T t) {
        return new Result<>(0, "success", t);
    }
}
