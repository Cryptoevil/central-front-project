package pro.cryptoevil.front.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.cryptoevil.front.common.model.Response;
import pro.cryptoevil.front.service.api.AdminService;

import java.util.List;

@RestController
@RequestMapping("/front-service/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("addSubscriber")
    public Response<Long> addSubscriber(@RequestParam Long id) {
        return this.adminService.addSubscriber(id);
    }

    @GetMapping("removeSubscriber")
    public Response<Long> removeSubscriber(@RequestParam Long id) {
        return this.adminService.removeSubscriber(id);
    }

    @GetMapping("subscribersList")
    public Response<List<Long>> getSubscribersList() {
        return this.adminService.subscribersList();
    }

    @GetMapping("addAdmin")
    public Response<Long> addAdmin(@RequestParam Long id) {
        return this.adminService.addAdmin(id);
    }

    @GetMapping("removeAdmin")
    public Response<Long> removeAdmin(@RequestParam Long id) {
        return this.adminService.removeAdmin(id);
    }

    @GetMapping("adminsList")
    public Response<List<Long>> getAdminsList() {
        return this.adminService.adminsList();
    }
}
