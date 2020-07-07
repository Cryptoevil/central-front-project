package pro.cryptoevil.front.integrations.cryptographer.client;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CryptographerClientFallbackFactory implements FallbackFactory<CryptographerClient> {

    @Override
    public CryptographerClient create(Throwable throwable) {
        return new CryptographerClientFallback(throwable);
    }
}
