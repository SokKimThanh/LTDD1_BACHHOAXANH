package tdc.edu.login;

public enum AccountLevel {
    ADMIN(0),
    CUSTOMER(1),
    EMPLOYEE(2);

    private final int levelCode;

    AccountLevel(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getLevelCode() {
        return this.levelCode;
    }
}

