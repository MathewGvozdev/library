package com.mathewgv.library.dao.filter;

import java.util.ArrayList;
import java.util.List;

public abstract class SelectFilter {

    private final Integer limit;
    private final Integer offset;

    private final List<Object> parameters = new ArrayList<>();
    private final List<String> conditions = new ArrayList<>();

    public SelectFilter(Integer page) {
        this.limit = 10;
        this.offset = (page - 1) * 10;
    }

    public abstract String getSqlRequest(String selectSql);

    protected abstract void initConditions();

    public int getConditionsSize() {
        return getConditions().size() + 2; //limit and offset conditions also
    }

    public Object getParameterValue(int number) {
        return getParameters().get(number);
    }

    protected void addParameter(Object parameter) {
        parameters.add(parameter);
    }

    protected void addCondition(String condition) {
        conditions.add(condition);
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    protected List<Object> getParameters() {
        return parameters;
    }

    protected List<String> getConditions() {
        return conditions;
    }
}
