package ma.yassine.hopital.security.repo;

import ma.yassine.hopital.security.entities.AppRole;
import ma.yassine.hopital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {
}
