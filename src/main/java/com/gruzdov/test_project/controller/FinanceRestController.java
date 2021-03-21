package com.gruzdov.test_project.controller;

import com.gruzdov.test_project.model.Finance;
import com.gruzdov.test_project.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("finances")
public class FinanceRestController {

    private final FinanceService financeService;

    @Autowired
    public FinanceRestController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping
    public ResponseEntity<List<Finance>> getAllFinance() {
        return ResponseEntity.ok().body(financeService.getAllFinance());
    }

    @GetMapping("{id}")
    public ResponseEntity<Finance> getFinanceById(@PathVariable Long id) {
        return ResponseEntity.ok().body(financeService.getFinanceById(id));
    }

    @PostMapping
    public ResponseEntity<Finance> createFinance(@RequestBody Finance finance) {
        return ResponseEntity.ok().body(this.financeService.createFinance(finance));
    }

    @PutMapping
    public ResponseEntity<Finance> updateFinance(@RequestBody Finance finance) {
        return ResponseEntity.ok().body(this.financeService.updateFinance(finance));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteFinance(@PathVariable Long id) {
        this.financeService.deleteFinance(id);
        return HttpStatus.OK;
    }
}
