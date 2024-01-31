package top.tobycold.wordservice.common;

import lombok.Getter;

/**
 * 这里的所有参数都用private修饰，有没有提供get方法，使spring在处理时，没能拿到数据
 * 解决方案：
 *      1.提供get方法
 *      2.将成员变量改为用public修饰
 * 以下采用第一种方法解决
 * @param <T>
 */
@Getter
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
