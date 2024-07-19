package ma.zs.zyn.dao.criteria.core.configuration;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;

public class BankTransferConfigurationCriteria extends  BaseCriteria  {

    private String iban;
    private String ibanLike;
    private String swift;
    private String swiftLike;
    private String accountHolderName;
    private String accountHolderNameLike;
    private String bankName;
    private String bankNameLike;
    private String bankAddress;
    private String bankAddressLike;



    public BankTransferConfigurationCriteria(){}

    public String getIban(){
        return this.iban;
    }
    public void setIban(String iban){
        this.iban = iban;
    }
    public String getIbanLike(){
        return this.ibanLike;
    }
    public void setIbanLike(String ibanLike){
        this.ibanLike = ibanLike;
    }

    public String getSwift(){
        return this.swift;
    }
    public void setSwift(String swift){
        this.swift = swift;
    }
    public String getSwiftLike(){
        return this.swiftLike;
    }
    public void setSwiftLike(String swiftLike){
        this.swiftLike = swiftLike;
    }

    public String getAccountHolderName(){
        return this.accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName){
        this.accountHolderName = accountHolderName;
    }
    public String getAccountHolderNameLike(){
        return this.accountHolderNameLike;
    }
    public void setAccountHolderNameLike(String accountHolderNameLike){
        this.accountHolderNameLike = accountHolderNameLike;
    }

    public String getBankName(){
        return this.bankName;
    }
    public void setBankName(String bankName){
        this.bankName = bankName;
    }
    public String getBankNameLike(){
        return this.bankNameLike;
    }
    public void setBankNameLike(String bankNameLike){
        this.bankNameLike = bankNameLike;
    }

    public String getBankAddress(){
        return this.bankAddress;
    }
    public void setBankAddress(String bankAddress){
        this.bankAddress = bankAddress;
    }
    public String getBankAddressLike(){
        return this.bankAddressLike;
    }
    public void setBankAddressLike(String bankAddressLike){
        this.bankAddressLike = bankAddressLike;
    }


}
