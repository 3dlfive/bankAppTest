package com.denystry.bankapp.repository;

import com.denystry.bankapp.dao.CustomerLocalDao;
import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CustomerDaoDBImpl implements CustomerLocalDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer save(Customer obj) {
        entityManager.persist(obj);
        return obj;
    }

    @Override
    public boolean delete(Customer obj) {
        entityManager.remove(obj);
        return true;
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        for (Customer customer : entities) {
            entityManager.remove(customer);
        }
    }

    @Override
    public void saveAll(List<Customer> entities) {
        for (Customer customer : entities) {
            entityManager.persist(customer);
        }
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
            return true;
        }
        return false;
    }

    @Override
    public Customer getOne(long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public void deleteAccountFromCustomer(Long customerId, Long accountId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if (customer != null) {
            List<Account> accounts = customer.getAccounts();
            accounts.removeIf(account -> account.getId().equals(accountId));
            Account account = entityManager.find(Account.class, accountId);
            if (account != null) {
                entityManager.remove(account);
            }
        }
    }

    @Override
    public Account createAccountForCustomer(Long customerId, Account account) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if (customer != null) {
            account.setCustomer(customer);
            entityManager.persist(account);
            customer.getAccounts().add(account);
            entityManager.merge(customer);
            return account;
        }
        return null;
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if (customer != null) {
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAge(updatedCustomer.getAge());
            return entityManager.merge(customer);
        }
        return null;
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        Optional<Account> account = entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
                .setParameter("number", accountNumber)
                .getResultStream()
                .findFirst();
        if (account.isPresent()) {
            Account acc = account.get();
            acc.setBalance(acc.getBalance() + amount);
            entityManager.merge(acc);
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        Optional<Account> account = entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
                .setParameter("number", accountNumber)
                .getResultStream()
                .findFirst();
        if (account.isPresent()) {
            Account acc = account.get();
            if (acc.getBalance() >= amount) {
                acc.setBalance(acc.getBalance() - amount);
                entityManager.merge(acc);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Optional<Account> fromAccount = entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
                .setParameter("number", fromAccountNumber)
                .getResultStream()
                .findFirst();

        Optional<Account> toAccount = entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
                .setParameter("number", toAccountNumber)
                .getResultStream()
                .findFirst();

        if (fromAccount.isPresent() && toAccount.isPresent()) {
            Account fromAcc = fromAccount.get();
            Account toAcc = toAccount.get();
            if (fromAcc.getBalance() >= amount) {
                fromAcc.setBalance(fromAcc.getBalance() - amount);
                toAcc.setBalance(toAcc.getBalance() + amount);
                entityManager.merge(fromAcc);
                entityManager.merge(toAcc);
                return true;
            }
        }
        return false;

    }
}
