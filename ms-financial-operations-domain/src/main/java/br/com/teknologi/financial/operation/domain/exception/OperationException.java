package br.com.teknologi.financial.operation.domain.exception;

public class OperationException extends RuntimeException{

    private final String code;

    public OperationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
