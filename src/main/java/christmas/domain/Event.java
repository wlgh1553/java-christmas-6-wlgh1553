package christmas.domain;

import static christmas.domain.Menu.CHAMPAGNE;

import christmas.repository.Menus;
import java.util.function.BiFunction;
import java.util.function.Function;

public enum Event {
    D_DAY_EVENT("크리스마스 디데이 할인", Day::isDdayPeriod, Event::getDdayBenefit),
    WEEKDAY_EVENT("평일 할인", Day::isWeekday, Event::getWeekdayBenefit),
    WEEKEND_EVENT("주말 할인", Day::isWeekend, Event::getWeekendBenefit),
    SPECIAL_EVENT("특별 할인", Day::isSpecialDay, Event::getSpecialBenefit),
    GIFT_EVENT("증정 이벤트", cost -> cost >= 120_000, Event::getGiftEventBenefit);

    private final String eventName;
    private final Function<Integer, Boolean> benefitCondition;
    private final BiFunction<Integer, Menus, Integer> calculateBenefit;

    Event(String eventName, Function<Integer, Boolean> benefitCondition,
          BiFunction<Integer, Menus, Integer> calculateBenefit) {
        this.eventName = eventName;
        this.benefitCondition = benefitCondition;
        this.calculateBenefit = calculateBenefit;
    }

    public Integer getBenefit(Integer date, Menus menus) {
        return calculateBenefit.apply(date, menus);
    }

    public String getEventName() {
        return eventName;
    }

    private static Integer getDdayBenefit(Integer date, Menus menus) {
        if (!D_DAY_EVENT.benefitCondition.apply(date)) {
            return 0;
        }
        return 1000 + (date - 1) * 100;
    }

    private static Integer getWeekdayBenefit(Integer date, Menus menus) {
        if (!WEEKDAY_EVENT.benefitCondition.apply(date)) {
            return 0;
        }
        return menus.getNumberOfDessert() * 2023;
    }

    private static Integer getWeekendBenefit(Integer date, Menus menus) {
        if (!WEEKEND_EVENT.benefitCondition.apply(date)) {
            return 0;
        }
        return menus.getNumberOfMain() * 2023;
    }

    private static Integer getSpecialBenefit(Integer date, Menus menus) {
        if (!SPECIAL_EVENT.benefitCondition.apply(date)) {
            return 0;
        }
        return 1000;
    }

    private static Integer getGiftEventBenefit(Integer date, Menus menus) {
        if (!GIFT_EVENT.benefitCondition.apply(menus.getCostSum())) {
            return 0;
        }
        return CHAMPAGNE.getCost();
    }
}
