package pro.cryptoevil.front.service.telegram.node.core;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

public abstract class BaseNode {

    private NodeState state = NodeState.NEW;

    public final BotApiMethod<? extends Serializable> obtain(Update update) {
        BotApiMethod<? extends Serializable> botApiMethod = this.source(update);
        if (this.state == NodeState.NEW) {
            this.state = NodeState.INITIALIZED;
        }
        return botApiMethod;
    }

    protected abstract BotApiMethod<? extends Serializable> source(Update update);

    public NodeState getState() {
        return this.state;
    }

    public void complete() {
        this.state = NodeState.COMPLETED;
    }
}
