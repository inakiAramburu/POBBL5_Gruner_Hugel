package gruner.huger.grunerhugel.config;

public enum URI {
    LOGIN("/login", "login"),
    REGISTER("/register", "user/user-form"),
    HOME_USER_NO_FARM("/main", "simulation/simulation-form"),
    HOME_USER_FARM("/simulation", "simulation/simulation"),
    HOME_ADMIN("/admin", "user/user-list"),
    HOME_INVESTOR("/investor", "investor");

    private String path;
    private String view;

    private URI(String path, String view) {
        this.path = path;
        this.view = view;
    }

    public String getPath() {
        return path;
    }

    public String getView() {
        return view;
    }
}
