package christmas.infrastructure;

import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuRepository;
import christmas.domain.menu.Menus;
import christmas.domain.menu.specific.AppetizerMenu;
import christmas.domain.menu.specific.BeverageMenu;
import christmas.domain.menu.specific.DessertMenu;
import christmas.domain.menu.specific.MainMenu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class SimpleMenuRepository implements MenuRepository {

    private static final String NOT_FOUND_MENU_MESSAGE = "존재하지 않는 메뉴입니다.";

    private final Map<String, Menu> store = new HashMap<>();

    public SimpleMenuRepository() {
        store.put("양송이수프", AppetizerMenu.of("양송이수프", 6_000));
        store.put("타파스", AppetizerMenu.of("타파스", 5_500));
        store.put("시저샐러드", AppetizerMenu.of("시저샐러드", 8_000));
        store.put("티본스테이크", MainMenu.of("티본스테이크", 55_000));
        store.put("바비큐립", MainMenu.of("바비큐립", 54_000));
        store.put("해산물파스타", MainMenu.of("해산물파스타", 35_000));
        store.put("크리스마스파스타", MainMenu.of("크리스마스파스타", 25_000));
        store.put("초코케이크", DessertMenu.of("초코케이크", 15_000));
        store.put("아이스크림", DessertMenu.of("아이스크림", 5_000));
        store.put("제로콜라", BeverageMenu.of("제로콜라", 3_000));
        store.put("레드와인", BeverageMenu.of("레드와인", 60_000));
        store.put("샴페인", BeverageMenu.of("샴페인", 25_000));
    }

    @Override
    public Menu findByName(String name) {
        Menu foundMenu = store.get(name);
        requireNonNull(foundMenu, () -> new NoSuchElementException(NOT_FOUND_MENU_MESSAGE));

        return foundMenu;
    }

    @Override
    public Menus findAll() {
        List<Menu> menusData = store.values().stream().toList();

        return Menus.from(menusData);
    }
}
