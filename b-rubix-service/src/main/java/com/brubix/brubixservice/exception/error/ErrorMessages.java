
package com.brubix.brubixservice.exception.error;

public interface ErrorMessages {

    String INVALID_PAYLOAD = "Request payload is malformed or invalid";

    String INTERNAL_ERROR = "Internal server error occurred";

    String INVALID_HEADER = "Required header is missing";

    String FAIL_AUTH = "Authentication failed";

    String UNSUPPORTED_API = "Unsupported API";

    String INVALID_METHOD = "Invalid Method";
}
