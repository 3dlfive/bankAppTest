package com.denystry.bankapp.repository;

import com.denystry.bankapp.dao.AccountLocalDao;
import com.denystry.bankapp.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountDaoDBImpl implements AccountLocalDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account save(Account obj) {
        entityManager.persist(obj);
        return obj;
    }

    @Override
    public boolean delete(Account obj) {
        entityManager.remove(obj);
        return true;
    }

    @Override
    public void deleteAll(List<Account> entities) {
        for (Account account : entities) {
            entityManager.remove(account);
        }
    }

    @Override
    public void saveAll(List<Account> entities) {
        for (Account account : entities) {
            entityManager.persist(account);
        }
    }

    @Override
    public List<Account> findAll() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
            return true;
        }
        return false;
    }

    @Override
    public Account getOne(long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        // Implement withdraw operation
        return false;
    }

    @Override
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        // Implement transfer operation
        return false;
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        // Implement deposit operation
        return false;
    }

    @Override
    public Account findByNumber(String accountNumber) {
        // Implement finding account by number
        return null;
    }
}
