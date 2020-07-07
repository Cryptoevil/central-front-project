package pro.cryptoevil.front.service.telegram.node.impl.cryptographer;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.cryptoevil.front.integrations.cryptographer.CryptographerService;
import pro.cryptoevil.front.service.telegram.node.core.BaseNode;
import pro.cryptoevil.front.service.telegram.node.core.NodeState;

import java.io.Serializable;

public class CryptographerNode extends BaseNode {

    private enum Mode {
        ENCODE, DECODE, UNSELECTED
    }

    private Mode cryptographerMode;
    private CryptographerService cryptographerService;

    public CryptographerNode(CryptographerService cryptographerService) {
        this.cryptographerService = cryptographerService;
        this.cryptographerMode = Mode.UNSELECTED;
    }

    @Override
    protected BotApiMethod<? extends Serializable> source(Update update) {
        StringBuilder sb = new StringBuilder();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        String text = update.getMessage().getText();

        if (this.getState() == NodeState.NEW) {
            sb.append("Select mode: E or D");
        }

        if (this.getState() == NodeState.INITIALIZED) {
            switch (text) {
                case "E":
                    this.cryptographerMode = Mode.ENCODE;
                    return sendMessage.setText("Selected Encode mode.");
                case "D":
                    this.cryptographerMode = Mode.DECODE;
                    return sendMessage.setText("Selected Decode mode.");
            }

            switch (this.cryptographerMode) {
                case ENCODE:
                    sb.append(this.cryptographerService.encodeData(text));
                    break;
                case DECODE:
                    sb.append(this.cryptographerService.decodeData(text));
                    break;
            }
        }

        sendMessage.setText(sb.toString());
        return sendMessage;
    }
}
