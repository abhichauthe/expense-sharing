package com.expensesharing.repository;

import com.expensesharing.model.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {
    List<ExpenseSplit> findByUserId(Long userId);
}