package christmas;

import christmas.global.config.PresentationConfig;
import christmas.presentation.controller.ChristmasEventController;

public class Application {

    public static void main(String[] args) {
        ChristmasEventController controller = PresentationConfig.eventController();

        controller.run();
    }
}
