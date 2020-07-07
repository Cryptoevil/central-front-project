package pro.cryptoevil.front.api.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.cryptoevil.front.service.telegram.BotResponseListener;
import pro.cryptoevil.front.service.telegram.MessageHandler;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


import java.io.Serializable;

@Slf4j
@Component
public class CentralBot extends TelegramLongPollingBot implements BotResponseListener {

    @Value("${api.telegram.bot.token}")
    private String botToken;
    @Value("${api.telegram.bot.username}")
    private String botUserName;
    @Autowired
    private MessageHandler messageHandler;

    @Override
    public void onUpdateReceived(Update update) {
        log.info("onUpdateReceived -> chatId: {}", update.getMessage().getChatId());
        this.messageHandler.obtainUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return this.botUserName;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    private void sendMessage(BotApiMethod<? extends Serializable> method) {
        try {
            sendApiMethod(method);
        } catch (TelegramApiException e) {
            log.error("Error while send message: ", e);
        }
    }

    @Override
    public void onResponse(Flux<? extends BotApiMethod<? extends Serializable>> botApiMethod) {
        botApiMethod
                .subscribeOn(Schedulers.elastic())
                .log()
                .subscribe(this::sendMessage);
    }
}
