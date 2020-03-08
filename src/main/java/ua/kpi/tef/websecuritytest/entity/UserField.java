package ua.kpi.tef.websecuritytest.entity;

public enum UserField {
    USER_NAME("username");

    private final String field;

    UserField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
