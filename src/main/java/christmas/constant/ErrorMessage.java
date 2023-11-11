package christmas.constant;

public enum ErrorMessage {
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    CANNOT_ONLY_DRINK("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    CANNOT_ORDER_MORE_THAN_20("[ERROR] 한 번에 최대 20개까지만 주문 가능합니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
