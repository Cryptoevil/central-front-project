package pro.cryptoevil.front.integrations.cryptographer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pro.cryptoevil.front.common.model.Response;
import pro.cryptoevil.front.integrations.cryptographer.client.CryptographerClient;

@Slf4j
@Service
public class CryptographerService {

    @Autowired
    private CryptographerClient client;

    @Nullable
    public String encodeData(String data) {
        Response<String> response = this.client.encode(data);
        if (response.getCode() == 200) {
            log.info("encodeData -> Encoded data, result: {}", response.getData());
            return response.getData();
        }
        log.warn("encodeData -> Error while encoding data, code: {}, message: {}",
                response.getCode(), response.getMessage());
        return null;
    }

    @Nullable
    public String decodeData(String data) {
        Response<String> response = this.client.decode(data);
        if (response.getCode() == 200) {
            log.info("decodeData -> Decoded data, result: {}", response.getData());
            return response.getData();
        }
        log.warn("decodeData -> Error while decoding data, code: {}, message: {}",
                response.getCode(), response.getMessage());
        return null;
    }
}
