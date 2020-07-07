package pro.cryptoevil.front.integrations.cryptographer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.cryptoevil.front.common.model.Response;

@FeignClient(
        name = "CENTRAL-SERVICE-TEST",
        path = "/central/api/cryptographer",
        fallbackFactory = CryptographerClientFallbackFactory.class
)
public interface CryptographerClient {

    @GetMapping("encode")
    Response<String> encode(@RequestParam String data);

    @GetMapping("decode")
    Response<String> decode(@RequestParam String data);
}
