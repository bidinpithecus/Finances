package data;

public enum Category {
    FOOD("Food"),
    FUN("Fun"),
    EDUCATION("Education"),
    HEALTH("Health"),
    TRANSPORTATION("Transportation"),
    ANOTHER("Another");

    private final String category;

    Category(String categoryString) {
        this.category = categoryString;
    }

    @Override
    public String toString() {
        return category;
    }
}

