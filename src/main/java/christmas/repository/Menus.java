package christmas.repository;

import static christmas.constant.ErrorMessage.CANNOT_ONLY_DRINK;
import static christmas.constant.ErrorMessage.CANNOT_ORDER_MORE_THAN_20;
import static christmas.domain.Menu.toMenu;

import christmas.domain.Menu;
import java.util.HashMap;
import java.util.Map;

public class Menus {
    private final Map<Menu, Integer> menus;

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
        return menus.entrySet().stream().noneMatch(entry -> !entry.getKey().isDrink());
    }

    private Boolean isMoreThan20() {
        return menus.values().stream().mapToInt(numberOfMenu -> numberOfMenu).sum()
                > 20;
    }

    public Integer getTotalCost() {
        return menus.entrySet().stream()
                .map(menu -> menu.getKey().getCost() * menu.getValue())
                .reduce(0, Integer::sum);
    }

    public void getMenuList() {
        //반환형은 바꿔야함
        //총 메뉴 리스트 반환
        //메뉴명 - 개수 형태의 리스트? 맵?으로 반환 예정
    }


    public Integer getNumberOfMain() {
        return menus.entrySet().stream().filter(menu -> menu.getKey().isMain())
                .map(mainMenu -> mainMenu.getValue())
                .reduce(0, Integer::sum);
    }

    public Integer getNumberOfDessert() {
        return menus.entrySet().stream().filter(menu -> menu.getKey().isDessert())
                .map(dessert -> dessert.getValue())
                .reduce(0, Integer::sum);
    }

}
