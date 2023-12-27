package ru.netology.etarakanova.domain;

public class LoanOperation extends Operation {
    private int loanId;

    public LoanOperation(int id, int customerId, int sum, String currency, String merchant) {
        super(id, customerId, sum, currency, merchant);
    }
}