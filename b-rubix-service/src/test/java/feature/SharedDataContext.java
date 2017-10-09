package feature;

import org.springframework.http.ResponseEntity;

public class SharedDataContext {

    private static String schoolCode;
    private static ResponseEntity<String> responseEntity;

    public static synchronized void setSchoolCode(String schoolCode) {
        SharedDataContext.schoolCode = schoolCode;
    }

    public static synchronized String getSchoolCode() {
        return SharedDataContext.schoolCode;
    }

    public static ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }

    public static void setResponseEntity(ResponseEntity<String> responseEntity) {
        SharedDataContext.responseEntity = responseEntity;
    }
}
