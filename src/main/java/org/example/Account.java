package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Account {
    private String accountNR;
    private BigDecimal balance;
    private Client client;

    public void depositMoney(BigDecimal money){
        if (money.compareTo(BigDecimal.ZERO)>0){
            balance = balance.add(money);
        }
    }
    public void withdrawMoney(BigDecimal money){
        if (money.compareTo(BigDecimal.ZERO)>0){
            balance = balance.subtract(money);
        }
    }
    public Account(String accountNR, BigDecimal balance, Client client) {
        this.accountNR = accountNR;
        this.balance = balance;
        this.client = client;
    }

    public String getAccountNR() {
        return accountNR;
    }

    public void setAccountNR(String accountNR) {
        this.accountNR = accountNR;
    }

    public BigDecimal getBalance() {
        //return balance;
        return balance.setScale(2,RoundingMode.DOWN);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNR, account.accountNR) && Objects.equals(balance, account.balance) && Objects.equals(client, account.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNR, balance, client);
    }
}
