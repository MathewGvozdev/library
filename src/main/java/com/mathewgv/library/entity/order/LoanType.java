package com.mathewgv.library.entity.order;

public enum LoanType {

    READING_ROOM("Читальный зал"),
    TO_HOME("На дом");

    private final String value;

    LoanType(String value) {
        this.value = value;
    }

    public static LoanType findByName(String name) {
        return switch (name) {
            case "Читальный зал" -> READING_ROOM;
            case "На дом" -> TO_HOME;
            default -> null;
        };
    }

    public String getValue() {
        return value;
    }
}
