package pro.cryptoevil.front.service.telegram;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import reactor.core.publisher.Flux;

import java.io.Serializable;

public interface BotResponseListener {

    void onResponse(Flux<? extends BotApiMethod<? extends Serializable>> botApiMethod);
}
