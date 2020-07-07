package pro.cryptoevil.front.common.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Response<T> {

    private int code;
    private String message;
    private T data;
}
