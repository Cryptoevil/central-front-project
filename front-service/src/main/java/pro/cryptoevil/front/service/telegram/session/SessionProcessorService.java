package pro.cryptoevil.front.service.telegram.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.cryptoevil.front.service.telegram.node.service.NodeFactoryService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SessionProcessorService {

    @Autowired
    private NodeFactoryService nodeFactoryService;
    private Map<Integer, Session> sessions = new HashMap<>();

    public Session exchange(Update update) {
        int userId = 0;

        if (update.getMessage() != null) {
            userId = update.getMessage().getFrom().getId();
        } else if (update.getCallbackQuery() != null) {
            userId = update.getCallbackQuery().getFrom().getId();
        }

        Session session = this.sessions.get(userId);
        if (session == null) {
            session = new Session(update, this.nodeFactoryService);
            this.sessions.put(userId, session);
        } else {
            session.update(update);
        }
        log.info("exchange -> Sessions count: {}", this.getSessionsCount());
        return session;
    }

    public int getSessionsCount() {
        return this.sessions.size();
    }

    public void flushSessions() {
        this.sessions.clear();
        log.info("flushSessions -> Killed all active sessions.");
    }
}
