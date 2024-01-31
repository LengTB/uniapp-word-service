package top.tobycold.wordservice.common;

public class BaseValues {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getID(){
        return threadLocal.get();
    }
    public static void setID(String id){
        threadLocal.set(id);
    }
    public static void clearID(){
        threadLocal.remove();
    }
}
