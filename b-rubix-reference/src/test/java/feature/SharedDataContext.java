package feature;

import org.springframework.http.ResponseEntity;

public class SharedDataContext {

    private static String institutionCode;
    private static ResponseEntity<String> responseEntity;

    public static synchronized void setInstitutionCode(String institutionCode) {
        SharedDataContext.institutionCode = institutionCode;
    }

    public static synchronized String getInstitutionCode() {
        return SharedDataContext.institutionCode;
    }

    public static ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }

    public static void setResponseEntity(ResponseEntity<String> responseEntity) {
        SharedDataContext.responseEntity = responseEntity;
    }
}
