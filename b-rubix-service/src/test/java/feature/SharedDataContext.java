package feature;

public class SharedDataContext {

    private static String schoolCode;

    public static synchronized void setSchoolCode(String schoolCode) {
        SharedDataContext.schoolCode = schoolCode;
    }

    public static synchronized String getSchoolCode() {
        return SharedDataContext.schoolCode;
    }
}
