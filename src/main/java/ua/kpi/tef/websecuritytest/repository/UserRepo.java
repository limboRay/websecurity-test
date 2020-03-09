package ua.kpi.tef.websecuritytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.tef.websecuritytest.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}