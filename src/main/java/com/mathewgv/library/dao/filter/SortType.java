package com.mathewgv.library.dao.filter;

public enum SortType {

    ID(" ORDER BY id "),
    ID_DESC(" ORDER BY id DESC");

    private final String condition;

    SortType(String sqlCondition) {
        this.condition = sqlCondition;
    }

    public String getCondition() {
        return condition;
    }
}
