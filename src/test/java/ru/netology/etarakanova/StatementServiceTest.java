package ru.netology.etarakanova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.etarakanova.domain.Operation;
import ru.netology.etarakanova.services.StatementService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StatementServiceTest extends OperationHistoryApiApplicationTest{
    @Autowired
    StatementService statementService;

    @Test
    void saveOperationTest() {
        Operation operation = new Operation(1, 1, 300, "RUB", "Monetka");

        statementService.saveOperation(operation);
        List<Operation> operations = statementService.getOperations(1);

        assertTrue(operations.contains(operation));
    }

    @Test
    void getOperationsForCustomerTest() {
        Operation operation1 = new Operation(1, 1, 300, "RUB", "Monetka");
        Operation operation2 = new Operation(1, 1, 200, "RUB", "Monetka");
        Operation operation3 = new Operation(2, 3, 100, "RUB", "Monetka");

        statementService.saveOperation(operation1);
        statementService.saveOperation(operation2);
        statementService.saveOperation(operation3);
        List<Operation> operations = statementService.getOperations(1);

        assertTrue(operations.contains(operation1));
        assertTrue(operations.contains(operation2));
        assertFalse(operations.contains(operation3));
    }


    @Test
    void removeOperationByIdTest() {
        int clientId = 1;
        Operation operation1 = new Operation(1, clientId, 200, "RUB", "Monetka");
        Operation operation2 = new Operation(2, clientId, 333, "RUB", "Monetka");

        statementService.saveOperation(operation1);
        statementService.saveOperation(operation2);
        statementService.removeOperationById(clientId, 1);
        List<Operation> operations = statementService.getOperations(clientId);

        assertFalse(operations.contains(operation1));
        assertTrue(operations.contains(operation2));
    }
}
