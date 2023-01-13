package gruner.huger.grunerhugel.config;

public enum URI {
    LOGIN("/login"),
    REGISTER("/register"),
    HOME_USER_NO_FARM("/main"),
    HOME_USER_FARM("/simulation"),
    HOME_ADMIN("/admin"),
    HOME_INVESTOR("/investor");

    private String path;

    private URI(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
