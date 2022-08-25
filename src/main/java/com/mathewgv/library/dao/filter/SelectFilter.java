package com.mathewgv.library.dao.filter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectFilter {

    protected static final String LIMIT_AND_OFFSET_FOR_QUERY = " LIMIT ? OFFSET ?";
    protected static final String AND_FOR_QUERY = " AND ";
    protected static final String HAVING_FOR_QUERY = "HAVING ";

    private final Integer limit;
    private final Integer offset;

    private final List<Object> parameters = new ArrayList<>();
    private final List<String> conditions = new ArrayList<>();

    public SelectFilter(Integer page, Integer limit) {
        this.limit = limit;
        this.offset = (page - 1) * limit;
    }

    public void setParamsToQuery(PreparedStatement preparedStatement) throws SQLException {
        for (int i = 0; i < this.getConditionsSize(); i++) {
            preparedStatement.setObject(i + 1, this.getParameterValue(i));
        }
    }

    public String getSqlRequest(String selectSql, SortType type) {
        initConditions();
        return selectSql + type.getCondition() + LIMIT_AND_OFFSET_FOR_QUERY;
    }

    protected void initConditions() {
        addParameter(getLimit());
        addParameter(getOffset());
    }

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
