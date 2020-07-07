package pro.cryptoevil.front.service.telegram.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessValidatorService {

    @Value("${service.telegram.bot.adminUserIds}")
    private List<Long> adminUserIds;
    @Value("${service.telegram.bot.subscriberUserIds}")
    private List<Long> subscriberUserIds;

    public boolean isAdmin(long userId) {
        for (long id : adminUserIds) {
            if (id == userId) return true;
        }
        return false;
    }

    public boolean isSubscriber(long userId) {
        for (long id : subscriberUserIds) {
            if (id == userId) return true;
        }
        return false;
    }

    public boolean addAdmin(long userId) {
        return this.adminUserIds.add(userId);
    }

    public boolean removeAdmin(long userId) {
        return this.adminUserIds.remove(userId);
    }

    public boolean addSubscriber(long userId) {
        return this.subscriberUserIds.add(userId);
    }

    public boolean removeSubscriber(long userId) {
        return this.subscriberUserIds.remove(userId);
    }

    public List<Long> getAdminUserIds() {
        return adminUserIds;
    }

    public List<Long> getSubscriberUserIds() {
        return subscriberUserIds;
    }
}
