package christmas.domain.menu;

public interface MenuRepository {

    Menu findByName(String name);

    Menus findAll();
}
