package ru.netology.etarakanova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.etarakanova.domain.Operation;
import ru.netology.etarakanova.services.AsyncInputOperationsService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AsyncInputOperationsServiceTest extends OperationHistoryApiApplicationTest{
    @Autowired
    AsyncInputOperationsService asyncInputOperationsService;

    @Test
    public void addNewOperationTest() {
        Operation operation = new Operation(1, 1, 500, "RUB", "Pyaterochka");
        boolean result = asyncInputOperationsService.addNewOperation(operation);
        assertTrue(result);
        assertEquals(1, asyncInputOperationsService.getOperations().size());
        assertEquals(operation, asyncInputOperationsService.getOperations().peek());
    }
}
