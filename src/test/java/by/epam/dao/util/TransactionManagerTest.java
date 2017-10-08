package by.epam.dao.util;

import by.epam.dao.exception.DAOException;
import org.junit.Test;

import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;

public class TransactionManagerTest {

    private static final TransactionManager MANAGER = TransactionManager.getInstance();

    @Test
    public void shouldBeginAndEndTransaction() throws DAOException {
        MANAGER.beginTransaction();
        assertEquals(true, MANAGER.isTransactionActive());

        MANAGER.endTransaction();
        assertEquals(false, MANAGER.isTransactionActive());
    }

    @Test
    public void shouldBeginOnlyOneTransaction() {
        EntityTransaction firstEntityTransaction = MANAGER.beginTransaction();
        EntityTransaction secondEntityTransaction = MANAGER.beginTransaction();
        EntityTransaction thirdEntityTransaction = MANAGER.beginTransaction();

        assertNotNull(firstEntityTransaction);
        assertNotNull(secondEntityTransaction);
        assertNotNull(thirdEntityTransaction);

        assertTrue(firstEntityTransaction == secondEntityTransaction);
        assertTrue(firstEntityTransaction == thirdEntityTransaction);
        assertTrue(secondEntityTransaction == thirdEntityTransaction);
    }

    @Test
    public void shouldNotThrowAnExceptionWhenTryEndTransactionTwice() throws DAOException {
        MANAGER.beginTransaction();
        assertEquals(true, MANAGER.isTransactionActive());

        MANAGER.endTransaction();
        assertEquals(false, MANAGER.isTransactionActive());
        MANAGER.endTransaction();
        assertEquals(false, MANAGER.isTransactionActive());
    }

  /*  @Test
    public void shouldNotThrowAnExceptionWhenTryUseTransactionWhenEntityManagerIsClosed() throws DAOException {
        MANAGER.beginTransaction();
        assertEquals(true, MANAGER.isTransactionActive());

        MANAGER.getEntityManager().close();
        assertEquals(false, MANAGER.getEntityManager().isOpen());

        MANAGER.endTransaction();
        assertEquals(false, MANAGER.isTransactionActive());
    }*/
}