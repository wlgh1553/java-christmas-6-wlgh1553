package christmas.service;

import static christmas.constant.ErrorMessage.INVALID_ORDER;

import christmas.repository.Menus;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuService {
    private final Menus menus;

    public MenuService(String order) {
        menus = new Menus(parseMenuInfo(splitOneMenu(order)));
    }

    private List<String> splitOneMenu(String order) {
        //입력된 메뉴들이 제대로 되었는지 확인 + 파싱해서 반환
        return Arrays.stream(order.split(",", -1)).toList();
    }

    private Map<String, Integer> parseMenuInfo(List<String> orders) {
        Map<String, Integer> menus = new HashMap<>();
        for (String order : orders) {
            String[] tokens = order.split("-", -1);
            checkValidToken(tokens);
            checkDuplication(menus, validMenuName(tokens[0]));
            menus.put(validMenuName(tokens[0]), validMenuCnt(tokens[1]));
        }
        return menus;
    }

    private void checkValidToken(String[] tokens) {
        if (tokens.length != 2) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private String validMenuName(String name) {
        if (name.trim().length() != name.length()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
        return name;
    }

    private Integer validMenuCnt(String numberOfMenus) {
        Integer number;
        try {
            number = Integer.valueOf(numberOfMenus);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }

        if (number >= 1) {
            return number;
        }
        throw new IllegalArgumentException(INVALID_ORDER.getMessage());
    }

    private void checkDuplication(Map<String, Integer> menus, String menuName) {
        if (menus.containsKey(menuName)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public List<String> getFormattedMenuInfos() {
        return new ArrayList<>(menus.getMenuNameAndNumber().entrySet().stream()
                .map(menu -> getFormattedMenuInfo(menu.getKey(), menu.getValue()))
                .collect(Collectors.toList()));
    }

    private String getFormattedMenuInfo(String menuName, Integer menuCnt) {
        return menuName + " " + menuCnt + "개";
    }

    public String getFormattedTotalCost() {
        DecimalFormat moneyFormat = new DecimalFormat("###,###원");
        return moneyFormat.format(menus.getCostSum());
    }

}
