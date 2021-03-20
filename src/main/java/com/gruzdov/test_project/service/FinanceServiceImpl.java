package com.gruzdov.test_project.service;

import com.gruzdov.test_project.exception.ResourceNotFoundException;
import com.gruzdov.test_project.model.Finance;
import com.gruzdov.test_project.repository.FinanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceRepository financeRepository;

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
            Finance financeUpdate = financeDb.get();
            financeUpdate.setId(finance.getId());
            financeUpdate.setPublicationDate(finance.getPublicationDate());
            financeUpdate.setInstrument(finance.getInstrument());
            financeUpdate.setCoast(finance.getCoast());
            financeRepository.save(financeUpdate);
            log.info("IN update - finance: {} successfully updated", finance);
            return financeUpdate;
        } else {
            throw new ResourceNotFoundException("Resource not found with id : " + finance.getId());
        }
    }

    @Override
    public List<Finance> getAllFinance() {
        List<Finance> financeList = this.financeRepository.findAll();
        log.info("IN getAll - finance: {} finance found", financeList.size());
        return financeList;
    }

    @Override
    public Finance getFinanceById(long financeId) {
        Optional<Finance> financeDb = this.financeRepository.findById(financeId);
        if (financeDb.isPresent()) {
            Finance result = financeDb.get();
            log.info("IN findById - finance: {} found by id: {}", result, financeId);
            return result;
        } else {
            throw new ResourceNotFoundException("Resource not found with id : " + financeId);
        }
    }

    @Override
    public void deleteFinance(long financeId) {
        Optional<Finance> financeDb = this.financeRepository.findById(financeId);
        if (financeDb.isPresent()) {
            this.financeRepository.delete(financeDb.get());
            log.info("IN delete - finance with id: {} successfully deleted", financeId);
        } else {
            throw new ResourceNotFoundException("Resource not found with id : " + financeId);
        }
    }
}
