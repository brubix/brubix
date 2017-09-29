
package com.brubix.brubixservice.exception.error;

public interface ErrorMessages {

    String INVALID_PAYLOAD = "Request payload is malformed or invalid";

    String INTERNAL_ERROR = "Internal server error occurred";

    String INVALID_HEADER = "Required header is missing";

    String FAIL_AUTH = "Authentication failed";

    String UNSUPPORTED_API = "Unsupported API";

    String INVALID_METHOD = "Invalid Method";

    String LOADING_ERROR = "Error occurred while saving the data";

    String INVALID_FILE = "Invalid file";

    String INVALID_COUNTRY_CODE = "Country code is not found in system";

    String INVALID_KYC_FILE_UPLOADS = "Number of KYC documents uploaded not matching with KYC data provided";

    String INVALID_SCHOOL_CODE = "School code is not found in system";
}
