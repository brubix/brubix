package feature;

import com.brubix.common.service.BrubixUserDetails;
import org.springframework.http.ResponseEntity;

public class SharedDataContext {

    private static String schoolCode;
    private static ResponseEntity<String> responseEntity;
    private static BrubixUserDetails brubixUserDetails;

    public static synchronized void setSchoolCode(String schoolCode) {
        SharedDataContext.schoolCode = schoolCode;
    }

    public static synchronized String getSchoolCode() {
        return SharedDataContext.schoolCode;
    }

    public static synchronized ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }

    public static synchronized void setResponseEntity(ResponseEntity<String> responseEntity) {
        SharedDataContext.responseEntity = responseEntity;
    }
    public static synchronized BrubixUserDetails getBrubixUserDetails() {
        return brubixUserDetails;
    }
    public static synchronized void setBrubixUserDetails(BrubixUserDetails brubixUserDetails) {
        SharedDataContext.brubixUserDetails = brubixUserDetails;
    }
}
