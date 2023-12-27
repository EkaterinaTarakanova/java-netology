package ru.netology.etarakanova.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation implements ConsolePrintable {
    private int id;
    private int customerId;
    private int sum;
    private String currency;
    private String merchant;

    @Override
    public void printToConsole() {
        System.out.println(String.format("id=%n, customerId=%d, sum=%d, currency=%s, merchant=%s", id, customerId, sum, currency, merchant));
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                ", merchant='" + merchant + '\'' +
                '}';
    }
}