package entity;

public enum GenderType {
    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non-Binary"),
    GENDERQUEER("Genderqueer"),
    OTHER("Other");

    private final String displayName;

    GenderType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
