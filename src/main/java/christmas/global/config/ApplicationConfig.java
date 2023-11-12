package christmas.global.config;

import christmas.application.OrderQueryService;
import christmas.application.OrderService;

public class ApplicationConfig {

    private ApplicationConfig() {
    }

    public static OrderService orderService() {
        return new OrderService(PersistenceConfig.menuRepository());
    }

    public static OrderQueryService orderQueryService() {
        return new OrderQueryService();
    }
}
