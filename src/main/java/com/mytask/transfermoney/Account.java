package com.mytask.transfermoney;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @NotNull(message = "The client number cannot be empty or null")
    private Long clientNumber;
    @Positive(message = "The client balance must be more than zero")
    @NotNull(message = "The client balance cannot be empty")
    private Double clientBalance;
    @NotBlank
    @NotNull(message = "The client name cannot be empty or null")
    private String clientName;
    private String clientPhone;
    private String clientAddress;
    private String clientDescription;


    public Account(Long clientNumber, Double clientBalance, String clientName, String clientPhone, String clientAddress, String clientDescription) {
        this.clientNumber = clientNumber;
        this.clientBalance = clientBalance;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.clientDescription = clientDescription;
    }

    public Account() {

    }

    @Override
    public String toString() {
        return "clientNumber = " + clientNumber +
                ", \nclientBalance = " + clientBalance +
                ", \nclientName = '" + clientName + '\'' +
                ", \nclientPhone = '" + clientPhone + '\'' +
                ", \nclientAddress = '" + clientAddress + '\'' +
                ", \nclientDescription = '" + clientDescription + '\'';
    }

    public Long getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(Long clientNumber) {
        this.clientNumber = clientNumber;
    }

    public Double getClientBalance() {
        return clientBalance;
    }

    public void setClientBalance(Double clientBalance) {
        this.clientBalance = clientBalance;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(clientNumber, account.clientNumber) &&
                Objects.equals(clientBalance, account.clientBalance) &&
                Objects.equals(clientName, account.clientName) &&
                Objects.equals(clientPhone, account.clientPhone) &&
                Objects.equals(clientAddress, account.clientAddress) &&
                Objects.equals(clientDescription, account.clientDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientNumber, clientBalance, clientName, clientPhone, clientAddress, clientDescription);
    }
}
