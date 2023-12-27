package ru.netology.etarakanova.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.etarakanova.configuration.OperationProperties;
import ru.netology.etarakanova.domain.Operation;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
public class AsyncInputOperationsService {
    @Getter
    private final Queue<Operation> operations = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProperties operationProcessingProperties;

    public boolean addNewOperation(Operation operation){
        System.out.println("Operation added for processing" + operation);
        return operations.offer(operation);
    }

    public void startProcessing(){
        statementService.getOperations();
        Thread t = new Thread(this::processQueue);
        t.start();
    }

    public void processQueue(){
        while (true){
            Operation operation = operations.poll();
            if (operation == null){
                try{
                    System.out.println("No operations");
                    Thread.sleep(operationProcessingProperties.getTimeout());
                } catch (InterruptedException e){
                    System.out.println(e);
                }
            }else {
                System.out.println("Processing operation" + operation);
                processOperation(operation);
            }
        }
    }
    private void processOperation(Operation operation){
        statementService.saveOperation(operation);
    }

    @PostConstruct
    public void init(){
        this.startProcessing();
    }
}