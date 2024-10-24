package com.project.trash.facility.repository;

import com.project.trash.facility.domain.Facility;
import com.project.trash.facility.domain.enums.FacilityApprovalStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

  Optional<Facility> findByFacilityIdAndMemberId(Long facilityId, String memberId);

  Optional<Facility> findByFacilityIdAndApprovalStatus(Long facilityId, FacilityApprovalStatus approvalStatus);
}
