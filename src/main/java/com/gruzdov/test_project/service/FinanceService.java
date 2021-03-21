package com.gruzdov.test_project.service;

import com.gruzdov.test_project.model.Finance;

import java.util.List;

public interface FinanceService {

    Finance createFinance(Finance finance);

    Finance updateFinance(Finance finance);

    List<Finance> getAllFinance();

    Finance getFinanceById(Long financeId);

    void deleteFinance(Long financeId);
}

