package com.user.managementuserapi.repository;

import com.user.managementuserapi.domain.Request;
import com.user.managementuserapi.domain.enums.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<Request, UUID> {

    public List<Request> findAllByUserUuid(UUID uuid);

    @Query("UPDATE request SET state = ?1 WHERE uuid = ?2")
    public Request updateRequestStage(RequestState requestState, UUID uuid);

}
