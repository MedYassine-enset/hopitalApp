package ma.yassine.hopital.security.service;

import ma.yassine.hopital.security.entities.AppRole;
import ma.yassine.hopital.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String email,String confirmPassword);
    AppRole addNewRola(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);
}
