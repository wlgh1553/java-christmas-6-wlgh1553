package christmas.domain;

import static christmas.domain.MenuCategory.APPETIZER;
import static christmas.domain.MenuCategory.DESSERT;
import static christmas.domain.MenuCategory.DRINK;
import static christmas.domain.MenuCategory.MAIN;

import java.util.Arrays;

public enum Menu {
    //그냥 메뉴 가격과 음료,디저트 등 종류를 알려주는 역할만
    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),
    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BBQ_RIBS(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),
    ZERO_COKE(DRINK, "제로콜라", 3_000),
    RED_WINE(DRINK, "레드와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000);

    private MenuCategory category;
    private String name;
    private Integer cost;

    Menu(MenuCategory category, String name, Integer cost) {
        this.category = category;
        this.name = name;
        this.cost = cost;
    }

    private static Menu noSuchMenu() {
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static Menu toMenu(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(name))
                .findAny()
                .orElse(noSuchMenu());
    }

    public static boolean isDrink(Menu menu) {
        return menu.category == DRINK;
    }

    public static boolean isMain(Menu menu) {
        return menu.category == MAIN;
    }

    public static boolean isDessert(Menu menu) {
        return menu.category == DESSERT;
    }

    public static Integer getCost(Menu menu) {
        return menu.cost;
    }

    public static String getName(Menu menu) {
        return menu.name;
    }
}
