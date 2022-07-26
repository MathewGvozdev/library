package com.mathewgv.library.entity.order;

public enum OrderStatus {

    OPENED("Открыт"),
    DECLINED("Отклонен"),
    LOANED("На выдаче"),
    CLOSED("Закрыт"),
    OVERDUE("Просрочен");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public static OrderStatus findByName(String name) {
        return switch (name) {
            case "Открыт" -> OPENED;
            case "Отклонен" -> DECLINED;
            case "На выдаче" -> LOANED;
            case "Закрыт" -> CLOSED;
            case "Просрочен" -> OVERDUE;
            default -> null;
        };
    }

    public String getValue() {
        return value;
    }
}
