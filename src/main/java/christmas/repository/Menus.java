package christmas.repository;

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

    public Boolean isOnlyDrink() {
        return false; //음료만 시켰는지
    }

    public Integer getNumberOfMain() {
        return 0; //메인 개수
    }

    public Integer getNumberOfDessert() {
        return 0; //디저트 개수
    }

}
