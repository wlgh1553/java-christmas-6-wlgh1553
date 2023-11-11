package christmas.view;

import java.util.List;

public class Outputs {
    public static void showWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void showPrevEventMessage(int date) {
        System.out.println("12월 " + date + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    //menus의 getMenuInfoList에서 받아와
    public static void showOrderMenus(List<String> menuInfos) {
        System.out.println("<주문 메뉴>");
        for (String info : menuInfos) {
            System.out.println(info);
        }
    }

    //service에서 받아와
    public static void showTotalCost(String formattedTotalCost) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formattedTotalCost);
    }

    //events에서 받아와
    public static void showGiftMenu(String giftMenuInfo) {
        System.out.println("<증정 메뉴>");
        System.out.println(giftMenuInfo);
    }

    //events에서 받아와
    public static void showBenefits(List<String> eventInfos) {
        System.out.println("<혜택 내역>");
        for (String info : eventInfos) {
            System.out.println(info);
        }
    }

    //events에서 총 benefit받아다가 service에서 포맷해서 가져오기
    public static void showTotalBenefitAmount(String formattedTotalCost) {
        System.out.println("<총혜택 금액>");
        System.out.println("-" + formattedTotalCost);
    }

    //서비스에서 잘~
    public static void showEstimatedCost(String cost) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(cost);
    }

    public static void showBadge(String badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }

}
