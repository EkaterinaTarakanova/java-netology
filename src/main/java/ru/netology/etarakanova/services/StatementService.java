package ru.netology.etarakanova.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.etarakanova.domain.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();
    public void saveOperation(Operation operation)
    {
        List<Operation> operations = storage.get(operation.getCustomerId());
        if (operations == null) {
            List<Operation> customerOperations = new ArrayList<>();
            customerOperations.add(operation);
            storage.put(operation.getCustomerId(), customerOperations);
        } else {
            operations.add(operation);
        }
    }

    public List<Operation> getOperations(int customerId) {
        List<Operation> operations = storage.get(customerId);
        return operations == null ? new ArrayList<>() : operations;
    }

    public String getOperations() {
        return storage.toString();
    }

    public void removeOperationById(int customerId, int operationId) {
        List<Operation> operations = storage.get(customerId);
        if (operations == null)
            return;
        operations.removeIf(o -> o.getId() == operationId);
    }
}