package com.gruzdov.test_project.repository;

import com.gruzdov.test_project.model.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, Long> {
}
