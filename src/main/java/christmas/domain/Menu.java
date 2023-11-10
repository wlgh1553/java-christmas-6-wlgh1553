package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_ORDER;
import static christmas.constant.MenuCategory.APPETIZER;
import static christmas.constant.MenuCategory.DESSERT;
import static christmas.constant.MenuCategory.DRINK;
import static christmas.constant.MenuCategory.MAIN;

import christmas.constant.MenuCategory;
import java.util.Arrays;

public enum Menu {
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

    private final MenuCategory category;
    private final String name;
    private final Integer cost;

    Menu(MenuCategory category, String name, Integer cost) {
        this.category = category;
        this.name = name;
        this.cost = cost;
    }

    public static Menu toMenu(String input) {
        return Arrays.stream(Menu.values())
                .filter(menu -> input.equals(menu.name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER.getMessage()));
    }

    public boolean isDrink() {
        return category == DRINK;
    }

    public boolean isMain() {
        return category == MAIN;
    }

    public boolean isDessert() {
        return category == DESSERT;
    }

    public Integer getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
