package pro.cryptoevil.front.service.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.cryptoevil.front.common.model.Response;
import pro.cryptoevil.front.service.telegram.service.AccessValidatorService;

import java.util.List;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AccessValidatorService accessValidatorService;


    public Response<Long> addSubscriber(Long id) {
        Response.ResponseBuilder<Long> responseBuilder = Response.builder();
        if (this.accessValidatorService.addSubscriber(id)) {
            responseBuilder
                    .code(200)
                    .message("OK")
                    .data(id);
        } else {
            responseBuilder
                    .code(400)
                    .message("Can't add subscriber!");
        }
        log.info("addSubscriber -> id: {}", id);
        return responseBuilder.build();
    }

    public Response<Long> removeSubscriber(Long id) {
        Response.ResponseBuilder<Long> responseBuilder = Response.builder();
        if (this.accessValidatorService.removeSubscriber(id)) {
            responseBuilder
                    .code(200)
                    .message("OK")
                    .data(id);
        } else {
            responseBuilder
                    .code(400)
                    .message("Can't remove subscriber!");
        }
        log.info("removeSubscriber -> id: {}", id);
        return responseBuilder.build();
    }

    public Response<List<Long>> subscribersList() {
        List<Long> subscribers = this.accessValidatorService.getSubscriberUserIds();
        Response.ResponseBuilder<List<Long>> responseBuilder = Response.builder();
        if (subscribers != null) {
            responseBuilder
                    .code(200)
                    .message("OK")
                    .data(subscribers);
        } else {
            responseBuilder
                    .code(400)
                    .message("Can't get subscribers list!");
        }
        log.info("subscribersList -> size: {}", subscribers != null ? subscribers.size() : "err");
        return responseBuilder.build();
    }

    public Response<Long> addAdmin(Long id) {
        Response.ResponseBuilder<Long> responseBuilder = Response.builder();
        if (this.accessValidatorService.addAdmin(id)) {
            responseBuilder
                    .code(200)
                    .message("OK")
                    .data(id);
        } else {
            responseBuilder
                    .code(400)
                    .message("Can't add admin!");
        }
        log.info("addAdmin -> id: {}", id);
        return responseBuilder.build();
    }

    public Response<Long> removeAdmin(Long id) {
        Response.ResponseBuilder<Long> responseBuilder = Response.builder();
        if (this.accessValidatorService.removeAdmin(id)) {
            responseBuilder
                    .code(200)
                    .message("OK")
                    .data(id);
        } else {
            responseBuilder
                    .code(400)
                    .message("Can't remove admin!");
        }
        log.info("removeAdmin -> id: {}", id);
        return responseBuilder.build();
    }

    public Response<List<Long>> adminsList() {
        List<Long> admins = this.accessValidatorService.getAdminUserIds();
        Response.ResponseBuilder<List<Long>> responseBuilder = Response.builder();
        if (admins != null) {
            responseBuilder
                    .code(200)
                    .message("OK")
                    .data(admins);
        } else {
            responseBuilder
                    .code(400)
                    .message("Can't get admins list!");
        }
        log.info("subscribersList -> size: {}", admins != null ? admins.size() : "err");
        return responseBuilder.build();
    }
}
