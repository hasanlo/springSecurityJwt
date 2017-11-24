package com.hasanlo.repository;

import com.hasanlo.entity.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {

    @Query("select r.role from com.hasanlo.entity.UserRole r where r.user.userName = ?1")
    public List<String> findRoleByUserUserName(String username);
}