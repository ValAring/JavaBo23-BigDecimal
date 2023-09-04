package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BankService {
    private List<Account> accounts = new ArrayList<>();

    public BankService() {
        this.accounts = accounts;
    }
    public String newAccount(Client client){
        String accountNR = generateAccountNr();
        Account account = new Account(accountNR, BigDecimal.ZERO, client);
        accounts.add(account);
        return accountNR;
    }
    public Account findAccount(String accNR){
        for(Account account : accounts){
            if(account.getAccountNR().equals(accNR)){
                return account;
            }
        }
        return null;
    }

    public void transferMoney(String fromAccNR, String toAccNR, BigDecimal amount) {
        Account fromANR = findAccount(fromAccNR);
        Account toANR = findAccount(toAccNR);

        if(fromANR !=null && toANR !=null && amount.compareTo(BigDecimal.ZERO)>0){
            fromANR.withdrawMoney(amount);
            toANR.depositMoney(amount);
        }
    }
    private String generateAccountNr(){
        return UUID.randomUUID().toString();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankService that = (BankService) o;
        return Objects.equals(accounts, that.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accounts);
    }
}
