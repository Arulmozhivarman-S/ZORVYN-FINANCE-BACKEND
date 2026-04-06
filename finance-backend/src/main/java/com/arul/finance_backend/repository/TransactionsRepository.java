package com.arul.finance_backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arul.finance_backend.dtos.CategoryResponseDto;
import com.arul.finance_backend.dtos.MonthlyTrendDto;
import com.arul.finance_backend.model.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    Page<Transactions> findByUserUserId(Long userId, Pageable pageable);

    
    @Query("SELECT COALESCE( SUM( CASE  WHEN t.transactionType = 'INCOME' THEN t.amount  ELSE -t.amount END ), 0) FROM Transactions t WHERE t.user.userId = :userId ")
    Double getNetBalance(@Param("userId") Long userId);


    @Query("""
        SELECT new com.yourpackage.MonthlyTrendDto(
            YEAR(t.createdAt),
            MONTH(t.createdAt),
            SUM(
                CASE 
                    WHEN t.transactionType = 'INCOME' THEN t.amount
                    ELSE -t.amount
                END
            )
        )
        FROM Transactions t
        WHERE t.user.userId = :userId
        GROUP BY YEAR(t.createdAt), MONTH(t.createdAt)
        ORDER BY YEAR(t.createdAt), MONTH(t.createdAt)
    """)
    List<MonthlyTrendDto> getMonthlyTrends(Long userId);


    
    @Query("""
        SELECT new com.yourpackage.CategoryResponseDto(t.category, SUM(t.amount))
        FROM Transactions t
        WHERE t.user.userId = :userId
        GROUP BY t.category
        """)
    List<CategoryResponseDto> getCategoryTotals(Long userId);
    



    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transactions t WHERE t.user.userId = :userId and t.transactionType=TransactionType.INCOME")
    Double getTotalIncome(@Param("userId") Long userId);

    

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transactions t where t.user.userId= :userId and t.transactionType=TransactionType.EXPENSE")
    Double getTotalExpense(@Param("userId") Long userId);

    
    List<Transactions> findTop5ByUserUserIdOrderByCreatedAtDesc(Long userId);

}
