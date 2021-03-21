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
        Optional<Finance> financeDb = this.financeRepository.findById(finance.getId());
        if (financeDb.isPresent()) {
            Finance financeUpdate = financeRepository.save(finance);
            log.info("IN update - finance: {} successfully updated", finance);
            return financeUpdate;
        } else {
            throw new ResourceNotFoundException("Resource with ID : " + finance.getId() + " not found");
        }
    }

    @Override
    public List<Finance> getAllFinance() {
        List<Finance> financeList = this.financeRepository.findAll();
        if (!financeList.isEmpty()) {
            log.info("IN getAll - finance: {} finance found", financeList.size());
            return financeList;
        } else {
            throw new ResourceNotFoundException("Resource is empty");
        }
    }

    @Override
    public Finance getFinanceById(Long financeId) {
        Optional<Finance> financeDb = this.financeRepository.findById(financeId);
        if (financeDb.isPresent()) {
            Finance result = financeDb.get();
            log.info("IN findById - finance: {} found by id: {}", result, financeId);
            return result;
        } else {
            throw new ResourceNotFoundException("Resource with ID : " + financeId + " not found");
        }
    }

    @Override
    public void deleteFinance(Long financeId) {
        Optional<Finance> financeDb = this.financeRepository.findById(financeId);
        if (financeDb.isPresent()) {
            this.financeRepository.delete(financeDb.get());
            log.info("IN delete - finance with id: {} successfully deleted", financeId);
        } else {
            throw new ResourceNotFoundException("Resource with ID : " + financeId + " not found");
        }
    }
}
