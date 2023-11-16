package christmas.global.config;

import christmas.presentation.controller.ChristmasEventController;

public class PresentationConfig {

    private PresentationConfig() {
    }

    public static ChristmasEventController eventController() {
        return new ChristmasEventController(
                ApplicationConfig.orderService(),
                ApplicationConfig.orderQueryService());
    }
}
