package com.user.managementuserapi.repository;

import com.user.managementuserapi.domain.RequestStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, UUID> {

    public List<RequestStage> findAllByRequestUuid(UUID uuid);

}
