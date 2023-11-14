package christmas.view;

import java.util.List;

public class Outputs {
    public static void showWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void showPrevEventMessage(int date) {
        System.out.println("12월 " + date + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public static void showOrderMenus(List<String> menuInfos) {
        System.out.println("<주문 메뉴>");
        for (String info : menuInfos) {
            System.out.println(info);
        }
        System.out.println();
    }

    public static void showTotalCost(String formattedTotalCost) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formattedTotalCost);
        System.out.println();
    }

    public static void showGiftMenu(List<String> giftMenuInfo) {
        System.out.println("<증정 메뉴>");
        for (String gift : giftMenuInfo) {
            System.out.println(gift);
        }
        System.out.println();
    }

    public static void showBenefits(List<String> eventInfos) {
        System.out.println("<혜택 내역>");
        for (String info : eventInfos) {
            System.out.println(info);
        }
        System.out.println();
    }

    public static void showTotalBenefitAmount(String formattedTotalCost) {
        System.out.println("<총혜택 금액>");
        System.out.println(formattedTotalCost);
        System.out.println();
    }

    public static void showEstimatedCost(String cost) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(cost);
        System.out.println();
    }

    public static void showBadge(String badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }

    public static void showErrorMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
