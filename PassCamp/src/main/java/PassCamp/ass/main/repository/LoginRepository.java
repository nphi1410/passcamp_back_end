/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.repository;

import PassCamp.ass.main.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AD
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

    @Query("SELECT l.loginId FROM Login l ORDER BY l.loginId DESC LIMIT 1")
    String findLastLoginId();

    Login findByUsername(String username);
}
