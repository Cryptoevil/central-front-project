package pro.cryptoevil.front.service.telegram.node.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pro.cryptoevil.front.integrations.cryptographer.CryptographerService;
import pro.cryptoevil.front.service.telegram.Command;
import pro.cryptoevil.front.service.telegram.node.core.BaseNode;
import pro.cryptoevil.front.service.telegram.node.impl.StartNode;
import pro.cryptoevil.front.service.telegram.node.impl.cryptographer.CryptographerNode;

import javax.validation.constraints.NotNull;

@Service
public class NodeFactoryService {

    @Value("${project.version}")
    private String projectVersion;
    @Autowired
    private CryptographerService cryptographerService;

    @Nullable
    public BaseNode getNode(@NotNull String command) {
        switch (command) {
            case Command.COMMAND_START:
                return new StartNode(this.projectVersion);
            case Command.COMMAND_CRYPTOR:
                return new CryptographerNode(this.cryptographerService);
            default:
                return null;
        }
    }
}
