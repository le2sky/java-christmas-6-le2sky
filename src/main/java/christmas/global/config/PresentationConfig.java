package christmas.global.config;

import christmas.presentation.controller.ChristmasEventConsoleController;

public class PresentationConfig {

    private PresentationConfig() {
    }

    public static ChristmasEventConsoleController eventController() {
        return new ChristmasEventConsoleController(
                ApplicationConfig.orderService(),
                ApplicationConfig.orderQueryService());
    }
}
