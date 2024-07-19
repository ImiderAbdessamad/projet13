package ma.zs.zyn.bean.core.configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "bank_transfer_configuration")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "bank_transfer_configuration_seq", sequenceName = "bank_transfer_configuration_seq", allocationSize = 1, initialValue = 1)
public class BankTransferConfiguration extends BaseEntity {


    @Column(length = 500)
    private String iban;

    @Column(length = 500)
    private String swift;

    @Column(length = 500)
    private String accountHolderName;

    @Column(length = 500)
    private String bankName;

    @Column(length = 500)
    private String bankAddress;


    public BankTransferConfiguration() {
        super();
    }

    public BankTransferConfiguration(Long id) {
        this.id = id;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_transfer_configuration_seq")
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSwift() {
        return this.swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return this.bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransferConfiguration bankTransferConfiguration = (BankTransferConfiguration) o;
        return id != null && id.equals(bankTransferConfiguration.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

