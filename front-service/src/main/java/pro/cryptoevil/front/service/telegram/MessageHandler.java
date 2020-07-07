package pro.cryptoevil.front.service.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.cryptoevil.front.service.telegram.service.AccessValidatorService;
import pro.cryptoevil.front.service.telegram.session.Session;
import pro.cryptoevil.front.service.telegram.session.SessionProcessorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@Slf4j
@Service
public class MessageHandler {

    @Autowired
    private BotResponseListener botResponseListener;
    @Autowired
    private AccessValidatorService accessValidatorService;
    @Autowired
    private SessionProcessorService sessionProcessorService;

    public void obtainUpdate(Update update) {
        long chatId = update.getMessage().getChatId();
        log.info("obtainUpdate -> chatId: {}, text: \"{}\",", chatId, update.getMessage().getText());
        Flux<BotApiMethod<? extends Serializable>> flux;

        if (this.accessValidatorService.isAdmin(chatId)) {
            flux = Flux.from(Mono.just(update))
                    .flatMap(tgUpdate -> Mono.just(sessionProcessorService.exchange(tgUpdate)))
                    .map(Session::proceed);
        } else if (this.accessValidatorService.isSubscriber(chatId)){
            flux = Flux.from(Mono.just(new SendMessage(chatId, "Subscription is active.")));
        } else {
            flux = Flux.from(Mono.just(new SendMessage(chatId, "Access denied.")));
        }

        this.botResponseListener.onResponse(flux);
    }
}
