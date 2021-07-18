package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryCriteria {
    private final EntityManager em;

    public List<Transaction> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> transaction = cq.from(Transaction.class);

        TypedQuery<Transaction> query = em.createQuery(cq);
        return query.getResultList();
    }
}
