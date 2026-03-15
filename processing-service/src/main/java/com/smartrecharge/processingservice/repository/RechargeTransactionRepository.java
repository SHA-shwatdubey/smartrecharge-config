package com.smartrecharge.processingservice.repository;

import com.smartrecharge.processingservice.entity.RechargeTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RechargeTransactionRepository extends JpaRepository<RechargeTransaction, Long> {

    List<RechargeTransaction> findByRechargeId(Long rechargeId);
}

