package christmas.global.config;

import christmas.domain.menu.MenuRepository;
import christmas.infrastructure.SimpleMenuRepository;

public class PersistenceConfig {

    private PersistenceConfig() {
    }

    public static MenuRepository menuRepository() {
        return new SimpleMenuRepository();
    }
}
