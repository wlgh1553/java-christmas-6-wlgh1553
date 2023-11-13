package christmas.controller;

import static christmas.constant.ErrorMessage.INVALID_DATE;
import static christmas.domain.Day.checkDateRange;
import static christmas.view.Inputs.getDate;
import static christmas.view.Inputs.getMenuOrder;
import static christmas.view.Outputs.showBadge;
import static christmas.view.Outputs.showBenefits;
import static christmas.view.Outputs.showEstimatedCost;
import static christmas.view.Outputs.showGiftMenu;
import static christmas.view.Outputs.showOrderMenus;
import static christmas.view.Outputs.showPrevEventMessage;
import static christmas.view.Outputs.showTotalBenefitAmount;
import static christmas.view.Outputs.showTotalCost;
import static christmas.view.Outputs.showWelcomeMessage;

import christmas.service.EventService;
import christmas.service.MenuService;

public class EventPlanner {
    public EventPlanner() {
        showWelcomeMessage();
        int date = getValidDate();
        MenuService menuService = getValidMenuService();
        EventService eventService = new EventService(date, menuService.getMenus());
        showEventResult(date, menuService, eventService);
    }

    private void showEventResult(Integer date, MenuService menuService, EventService eventService) {
        showPrevEventMessage(date);
        showOrderMenus(menuService.getFormattedMenuInfos());
        showTotalCost(menuService.getFormattedTotalCost());
        showGiftMenu(eventService.getGiftMenu());
        showBenefits(eventService.getBenefitDetails());
        showTotalBenefitAmount(eventService.getFormattedTotalBenefitCost());
        showEstimatedCost(eventService.getFormattedEstimatedCost());
        showBadge(eventService.getBadgeName());
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
