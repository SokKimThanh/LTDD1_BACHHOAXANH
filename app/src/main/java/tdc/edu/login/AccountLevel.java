package tdc.edu.login;

public enum AccountLevel {
    ADMIN(0),
    USER(1),
    GUEST(2);

    private final int levelCode;

    AccountLevel(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getLevelCode() {
        return this.levelCode;
    }
}

