package com.mathewgv.library.dao.filter;

import static java.util.stream.Collectors.joining;

public class UserFilter extends SelectFilter {

    private final String login;
    private final String password;
    private final Integer roleId;
    private final String roleTitle;

    private static final String LOGIN = "login = ?";
    private static final String PASSWORD = "password = ?";
    private static final String ROLE_ID = "role_id = ?";
    private static final String ROLE_TITLE = "r.title = ?";

    public UserFilter(Integer page, String login, String password, Integer roleId, String roleTitle) {
        super(page);
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.roleTitle = roleTitle;
    }

    public static UserFilterBuilder builder() {
        return new UserFilterBuilder();
    }

    @Override
    public String getSqlRequest(String selectSql) {
        initConditions();
        String whereSqlCondition;
        if (getConditions().isEmpty()) {
            whereSqlCondition = "\n";
        } else {
            whereSqlCondition = getConditions().stream()
                    .collect(joining(" AND ", "WHERE ", " "));
        }
        return selectSql + whereSqlCondition;
    }

    @Override
    protected void initConditions() {
        if (login != null) {
            addCondition(LOGIN);
            addParameter(login);
        }
        if (password != null) {
            addCondition(PASSWORD);
            addParameter(password);
        }
        if (roleId != null) {
            addCondition(ROLE_ID);
            addParameter(roleId);
        }
        if (roleTitle != null) {
            addCondition(ROLE_TITLE);
            addParameter(roleTitle);
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public static class UserFilterBuilder {
        private Integer page;
        private String login;
        private String password;
        private Integer roleId;
        private String roleTitle;

        UserFilterBuilder() {
        }

        public UserFilterBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public UserFilterBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserFilterBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserFilterBuilder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserFilterBuilder roleTitle(String roleTitle) {
            this.roleTitle = roleTitle;
            return this;
        }

        public UserFilter build() {
            return new UserFilter(page, login, password, roleId, roleTitle);
        }
    }
}
