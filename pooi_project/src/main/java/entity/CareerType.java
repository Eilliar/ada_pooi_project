package entity;

public enum CareerType {
    DIRECTOR("Director"),
    ACTOR("Actor"),
    SCREENWRITER("Screenwriter"),
    PRODUCER("Producer");

    private final String displayName;

    CareerType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
