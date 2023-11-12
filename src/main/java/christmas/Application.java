package christmas;

import christmas.global.config.PresentationConfig;
import christmas.presentation.controller.ChristmasEventConsoleController;

public class Application {

    public static void main(String[] args) {
        ChristmasEventConsoleController controller = PresentationConfig.eventController();

        controller.run();
    }
}
