package com.gruzdov.test_project.service;

import com.gruzdov.test_project.exception.ResourceNotFoundException;
import com.gruzdov.test_project.model.Finance;
import com.gruzdov.test_project.repository.FinanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepository financeRepository;

    @Autowired
    public FinanceServiceImpl(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    @Override
    public Finance createFinance(Finance finance) {
        Finance result = financeRepository.save(finance);
        log.info("IN create - finance: {} successfully created", finance);
        return result;
    }

    @Override
    public Finance updateFinance(Finance finance) {
        Long financeId = finance.getId();
        Optional<Finance> financeDb = financeRepository.findById(financeId);
        if (financeDb.isPresent()) {
            log.info("IN update - finance: {} successfully updated", finance);
            return financeRepository.save(finance);
        } else {
            log.warn("IN update - finance by id: {} no found", financeId);
            throw new ResourceNotFoundException(financeId);
        }
    }

    @Override
    public List<Finance> getAllFinance() {
        List<Finance> financeList = financeRepository.findAll();
        log.info("IN getAll - finance: {} finance found", financeList.size());
        return financeList;
    }

    @Override
    public Finance getFinanceById(Long financeId) {
        Optional<Finance> financeDb = financeRepository.findById(financeId);
        if (financeDb.isPresent()) {
            Finance result = financeDb.get();
            log.info("IN findById - finance: {} found by id: {}", result, financeId);
            return result;
        } else {
            log.warn("IN findById - finance by id: {} no found ", financeId);
            throw new ResourceNotFoundException(financeId);
        }
    }

    @Override
    public void deleteFinance(Long financeId) {
        Optional<Finance> financeDb = this.financeRepository.findById(financeId);
        if (financeDb.isPresent()) {
            financeRepository.deleteById(financeId);
            log.info("IN delete - finance with id: {} successfully deleted", financeId);
        } else {
            log.warn("IN delete - finance by id: {} no found ", financeId);
            throw new ResourceNotFoundException(financeId);
        }
    }
}
