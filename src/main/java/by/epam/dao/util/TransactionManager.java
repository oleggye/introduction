package by.epam.dao.util;

import by.epam.dao.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TransactionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionManager.class);

    private static final TransactionManager INSTANCE = new TransactionManager();

    private static final String PERSISTENCE_UNIT_NAME = "my-app";
    private static final EntityManager entityManager;

    static {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = factory.createEntityManager();
    }

    private final ThreadLocal<EntityTransaction> transactionThreadLocal = new ThreadLocal<>();

    private TransactionManager() {
    }

    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityTransaction beginTransaction() {
        EntityTransaction transaction = transactionThreadLocal.get();

        if (transaction == null || !transaction.isActive()) {
            transaction = entityManager.getTransaction();
            transactionThreadLocal.set(transaction);
            transaction.begin();
        }
        return transaction;
    }

    public void endTransaction() throws DAOException {
        EntityTransaction transaction = transactionThreadLocal.get();

        if (transaction != null && transaction.isActive()) {
            transaction = entityManager.getTransaction();
            commitTransaction(transaction);
        }
    }

    private void commitTransaction(EntityTransaction transaction) throws DAOException {
        try {
            transaction.commit();
        } catch (RuntimeException ex) {
            DAOException exception;

            String errorMessage = "Exception while committing a transaction";
            LOGGER.warn(errorMessage);
            exception = new DAOException(errorMessage, ex);

            try {
                transaction.rollback();
            } catch (RuntimeException e) {
                exception.addSuppressed(e);
            }
            throw exception;
        }
    }

    public void close() throws DAOException {
        try {
            entityManager.close();
        } catch (RuntimeException ex) {
            String errorMessage = "Exception while closing an entity manager";
            LOGGER.warn(errorMessage);
            throw new DAOException(errorMessage, ex);
        }
    }

    public boolean isTransactionActive() {
        EntityTransaction transaction = transactionThreadLocal.get();
        return !(transaction == null || transaction.isActive());
    }
}
