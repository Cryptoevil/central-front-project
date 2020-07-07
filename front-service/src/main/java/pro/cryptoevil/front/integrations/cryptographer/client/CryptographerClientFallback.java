package pro.cryptoevil.front.integrations.cryptographer.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.cryptoevil.front.common.model.Response;

@Slf4j
@AllArgsConstructor
public class CryptographerClientFallback implements CryptographerClient {

    private Throwable throwable;

    @Override
    public Response<String> encode(String data) {
        log.warn("encode -> Error while encoding data.", this.throwable);
        return this.errorResponse();
    }

    @Override
    public Response<String> decode(String data) {
        log.warn("decode -> Error while decoding data.", this.throwable);
        return this.errorResponse();
    }

    private <T>Response<T> errorResponse() {
        return Response.<T>builder()
                .code(700)
                .message("Request error.")
                .build();
    }
}
