package ru.netology.etarakanova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.etarakanova.controller.dto.OperationDTO;
import ru.netology.etarakanova.domain.Operation;
import ru.netology.etarakanova.services.AsyncInputOperationsService;
import ru.netology.etarakanova.services.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationsController {

    private final AsyncInputOperationsService inputOperationService;
    private final StatementService statementService;

    @GetMapping("{customerId}")
    public List<OperationDTO> getOperations(@PathVariable int customerId) {
        List<Operation> operations = statementService.getOperations(customerId);
        return operations.stream()
                .map(o -> new OperationDTO(o.getId(),o.getCustomerId(),o.getSum(),o.getCurrency(),o.getMerchant()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewOperation(@RequestBody OperationDTO operationDto) {
        Operation operation = new Operation(
                operationDto.getId(),
                operationDto.getCustomerId(),
                operationDto.getSum(),
                operationDto.getCurrency(),
                operationDto.getMerchant());
        inputOperationService.addNewOperation(operation);
    }

    @DeleteMapping("delete")
    public void deleteperationById(@RequestParam int customerId, @RequestParam int operationId) {
        statementService.removeOperationById(customerId, operationId);
    }
}