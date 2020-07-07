package pro.cryptoevil.front.service.telegram.node.impl;

import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.cryptoevil.front.service.telegram.Command;
import pro.cryptoevil.front.service.telegram.node.core.BaseNode;

import java.io.Serializable;

@AllArgsConstructor
public class StartNode extends BaseNode {

    private String projectVersion;

    @Override
    protected BotApiMethod<? extends Serializable> source(Update update) {
        StringBuilder sb = new StringBuilder("Welcome, ")
                .append(update.getMessage().getFrom().getFirstName())
                .append("!")
                .append("\n\n")
                .append("Commands list: ")
                .append("\n");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage
                .setText(this.buildCommandsList(sb)
                .append("Version: ")
                .append(this.projectVersion)
                .toString());
        this.complete();
        return sendMessage;
    }

    private StringBuilder buildCommandsList(StringBuilder sb) {
        return sb
                .append("-> ").append(Command.COMMAND_CRYPTOR)
                .append(" - ")
                .append("Cryptographer tool\n");
    }
}
