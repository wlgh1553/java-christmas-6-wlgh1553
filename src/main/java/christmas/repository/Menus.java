package christmas.repository;

import static christmas.constant.ErrorMessage.CANNOT_ONLY_DRINK;
import static christmas.constant.ErrorMessage.CANNOT_ORDER_MORE_THAN_20;
import static christmas.domain.Menu.toMenu;

import christmas.domain.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Menus {
    private final Map<Menu, Integer> menus;
    private final Integer MENU_NUMBER_LIMIT = 20;

    public Menus(Map<String, Integer> inputOrders) {
        menus = new HashMap<>();
        for (Map.Entry<String, Integer> entry : inputOrders.entrySet()) {
            menus.put(toMenu(entry.getKey()), entry.getValue());
        }
        checkValid();
    }

    private void checkValid() {
        if (isOnlyDrink()) {
            throw new IllegalArgumentException(CANNOT_ONLY_DRINK.getMessage());
        }
        if (isMoreThan20()) {
            throw new IllegalArgumentException(CANNOT_ORDER_MORE_THAN_20.getMessage());
        }
    }

    private Boolean isOnlyDrink() {
        return menus.entrySet().stream().allMatch(entry -> entry.getKey().isDrink());
    }

    private Boolean isMoreThan20() {
        return menus.values().stream().mapToInt(numberOfMenu -> numberOfMenu).sum()
                > MENU_NUMBER_LIMIT;
    }

    public Integer getNumberOfMain() {
        return menus.entrySet().stream().filter(menu -> menu.getKey().isMain())
                .map(Entry::getValue)
                .reduce(0, Integer::sum);
    }

    public Integer getNumberOfDessert() {
        return menus.entrySet().stream().filter(menu -> menu.getKey().isDessert())
                .map(Entry::getValue)
                .reduce(0, Integer::sum);
    }

    public Integer getCostSum() {
        return menus.entrySet().stream()
                .map(menu -> menu.getKey().getCost() * menu.getValue())
                .reduce(0, Integer::sum);
    }

    public Map<String, Integer> getMenuNameAndNumber() {
        Map<String, Integer> menuInfos = new HashMap<>();
        menus.forEach((key, value) -> menuInfos.put(key.getName(), value));
        return Collections.unmodifiableMap(menuInfos);
    }

}
