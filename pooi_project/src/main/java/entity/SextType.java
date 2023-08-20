package entity;

public enum SextType {
    MALE("Male"),
    FEMALE("Female");

    private final String displayName;

    SextType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
