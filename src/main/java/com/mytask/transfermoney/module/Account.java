package com.mytask.transfermoney.module;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientUsername;
    private String clientPassword;
    private Double clientBalance;
    private String clientName;
    private String clientPhone;
    private String clientAddress;
    private String clientDescription;
    private String clientProfilePic;


    public Account(Long id, String clientUsername,
                   String clientPassword,
                   Double clientBalance,
                   String clientName,
                   String clientPhone,
                   String clientAddress,
                   String clientDescription,
                   String clientProfilePic) {
        this.id = id;
        this.clientUsername = clientUsername;
        this.clientPassword = clientPassword;
        this.clientBalance = clientBalance;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.clientDescription = clientDescription;
        this.clientProfilePic = clientProfilePic;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
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

    public String getClientProfilePic() {
        return clientProfilePic;
    }

    public void setClientProfilePic(String clientProfilePic) {
        this.clientProfilePic = clientProfilePic;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientUsername='" + clientUsername + '\'' +
                ", clientPassword='" + clientPassword + '\'' +
                ", clientBalance=" + clientBalance +
                ", clientName='" + clientName + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientDescription='" + clientDescription + '\'' +
                ", clientProfilePic='" + clientProfilePic + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(clientUsername, account.clientUsername) &&
                Objects.equals(clientPassword, account.clientPassword) &&
                Objects.equals(clientBalance, account.clientBalance) &&
                Objects.equals(clientName, account.clientName) &&
                Objects.equals(clientPhone, account.clientPhone) &&
                Objects.equals(clientAddress, account.clientAddress) &&
                Objects.equals(clientDescription, account.clientDescription) &&
                Objects.equals(clientProfilePic, account.clientProfilePic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientUsername, clientPassword, clientBalance, clientName, clientPhone, clientAddress, clientDescription, clientProfilePic);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return clientPassword;
    }

    @Override
    public String getUsername() {
        return clientUsername;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
