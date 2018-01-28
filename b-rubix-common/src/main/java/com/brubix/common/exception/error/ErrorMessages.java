
package com.brubix.common.exception.error;

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

    String INVALID_DOCUMENT_FILE_UPLOADS = "Correct document details or attachments are not provided";

    String INVALID_INSTITUTION_CODE = "Institution code is not found in system";

    String INVALID_SUBJECT_NAME = "Subject is not found in system";

    String ALREADY_INSTITUTION_REGISTERED = "Institution already registered";

    String ALREADY_USER_REGISTERED = "User already registered";

    String ALREADY_PRESENT_FACEBOOK = "Provide facebook handle is associated with other institution";

    String ALREADY_PRESENT_TWITTER = "Provide twitter handle is associated with other institution";

    String ALREADY_PRESENT_GPLUS = "Provide gPlus handle associated with other institution";

    String ALREADY_PRESENT_LINKEDIN = "Provide linkedIn handle is associated with other institution";
}
