package christmas;

import christmas.application.OrderQueryService;
import christmas.application.OrderService;
import christmas.infrastructure.persistence.menu.SimpleMenuRepository;
import christmas.presentation.controller.ChristmasEventController;

public class Application {

    public static void main(String[] args) {
        ChristmasEventController christmasEventController =
                new ChristmasEventController(new OrderService(new SimpleMenuRepository()), new OrderQueryService());

        christmasEventController.run();
    }
}
