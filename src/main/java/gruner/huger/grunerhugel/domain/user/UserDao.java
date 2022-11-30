package gruner.huger.grunerhugel.domain.user;


public interface UserDao {

    boolean validate(String username, String password);
    
}
