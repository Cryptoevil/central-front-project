package pro.cryptoevil.front.service.telegram.session;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.cryptoevil.front.service.telegram.node.core.BaseNode;
import pro.cryptoevil.front.service.telegram.node.service.NodeFactoryService;
import pro.cryptoevil.front.service.telegram.node.core.NodeState;

import java.io.Serializable;

@Slf4j
public class Session {

    private Update update;
    private BaseNode baseNode;
    private NodeFactoryService nodeFactoryService;

    public Session(Update update, NodeFactoryService nodeFactoryService) {
        this.update = update;
        this.nodeFactoryService = nodeFactoryService;
    }

    public BotApiMethod<? extends Serializable> proceed() {
        if (this.verify()) {
            this.baseNode = this.nodeFactoryService.getNode(this.update.getMessage().getText());
            if (this.baseNode == null) {
                return this.getErrorResponse();
            }
        }
        return this.baseNode.obtain(this.update);
    }

    private boolean verify() {
        return this.baseNode == null
                || this.baseNode.getState() == NodeState.COMPLETED
                || this.update.getMessage().getText().startsWith("/");
    }

    private SendMessage getErrorResponse() {
        return new SendMessage(this.update.getMessage().getChatId(), "Undefined command.");
    }

    public void update(Update update) {
        this.update = update;
    }
}
