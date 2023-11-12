package christmas.controller;

import static christmas.constant.ErrorMessage.INVALID_DATE;
import static christmas.domain.Day.checkDateRange;
import static christmas.view.Inputs.getDate;
import static christmas.view.Inputs.getMenuOrder;
import static christmas.view.Outputs.showWelcomeMessage;

import christmas.service.MenuService;

public class EventPlanner {
    public EventPlanner() {
        showWelcomeMessage();
        int date = getValidDate();
        MenuService menuService = getValidMenuService();
    }

    private int getValidDate() {
        while (true) {
            try {
                return checkDateRange(toNumber(getDate()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private MenuService getValidMenuService() {
        while (true) {
            try {
                return new MenuService(getMenuOrder());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private Integer toNumber(String inputDay) {
        try {
            return Integer.valueOf(inputDay);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }
}
