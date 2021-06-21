package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.AcceptToken;
import com.gasstation.managementsystem.entity.AcceptTokenPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptTokenRepository extends JpaRepository<AcceptToken, AcceptTokenPrimaryKey> {
    @Modifying
    @Query("delete from AcceptToken a where a.token=?1")
    void deleteAllByToken(String token);

    @Modifying
    @Query("delete from AcceptToken a where a.userId=?1")
    void deleteAllByAccountId(int accountId);

    @Query("select a from AcceptToken a where a.token=?1 and a.userId=?2")
    AcceptToken getAcceptToken(String token, int id);

    AcceptToken findByToken(String token);

}
