package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiRepository extends JpaRepository<Api, Integer> {
    Optional<Api> findByPathAndMethod(String path, String method);

    @Modifying
    @Query("delete from Api")
    void deleteAll();

    @Query("select a from Api a inner join a.userTypeList u where u.id=?1")
    List<Api> findAllByUserTypeId(int userTypeId);
}
