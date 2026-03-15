package com.smartrecharge.rechargeservice.repository;

import com.smartrecharge.rechargeservice.entity.Recharge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {

    List<Recharge> findByCustomerId(Long customerId);
}

