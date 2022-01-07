package br.com.teknologi.financial.operation.rest.model.constant;

public abstract class HttpMessagesCodes {


    private HttpMessagesCodes() {}

    public static final String GENERIC_BAD_REQUEST_EXCEPTION = "400.000";
    public static final String REQUEST_WITH_INVALID_VALUE = "400.001";

    public static final String FIELD_VALIDATION_ERROR = "412.001";

    public static final String GENERIC_INTERNAL_ERROR_EXCEPTION = "500.000";


}
