package com.gruzdov.test_project.controller;

import com.gruzdov.test_project.model.Finance;
import com.gruzdov.test_project.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/finances")
    public ResponseEntity<List<Finance>> getAllFinance() {
        return ResponseEntity.ok().body(financeService.getAllFinance());
    }

    @GetMapping("/finances/{id}")
    public ResponseEntity<Finance> getFinanceById(@PathVariable long id) {
        return ResponseEntity.ok().body(financeService.getFinanceById(id));
    }

    @PostMapping("/finances")
    public ResponseEntity<Finance> createFinance(@RequestBody Finance finance) {
        return ResponseEntity.ok().body(this.financeService.createFinance(finance));
    }

    @PutMapping("/finances/{id}")
    public ResponseEntity<Finance> updateFinance(@PathVariable long id, @RequestBody Finance finance) {
        finance.setId(id);
        return ResponseEntity.ok().body(this.financeService.updateFinance(finance));
    }

    @DeleteMapping("/finances/{id}")
    public HttpStatus deleteFinance(@PathVariable long id) {
        this.financeService.deleteFinance(id);
        return HttpStatus.OK;
    }
}
