package christmas.service;

import static christmas.constant.ErrorMessage.INVALID_ORDER;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuService {
    //private final Menus menus;
    public Map<String, Integer> puhaha;

    public MenuService(String order) {
        //문자열의 타당성 검토가 끝나면 menus를 만듦
        puhaha = parseMenuInfo(splitOneMenu(order));
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

    private Integer validMenuCnt(String numberOfmenus) {
        Integer number;
        try {
            number = Integer.valueOf(numberOfmenus);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
        return number;
    }

    private void checkDuplication() {
        //중복되면 안 된다.
    }

    public List<String> getOrderedMenus() {
        return null;
        //주문 메뉴 전체를 반환해줌
    }

    public String getTotalCost() {
        return "hi";
        //포맷된 총 주문 금액 문자열로 보내주기
    }

}
