package ma.zs.zyn.ws.dto.configuration;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankTransferConfigurationDto  extends AuditBaseDto {

    private String iban  ;
    private String swift  ;
    private String accountHolderName  ;
    private String bankName  ;
    private String bankAddress  ;




    public BankTransferConfigurationDto(){
        super();
    }



    @Log
    public String getIban(){
        return this.iban;
    }
    public void setIban(String iban){
        this.iban = iban;
    }

    @Log
    public String getSwift(){
        return this.swift;
    }
    public void setSwift(String swift){
        this.swift = swift;
    }

    @Log
    public String getAccountHolderName(){
        return this.accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName){
        this.accountHolderName = accountHolderName;
    }

    @Log
    public String getBankName(){
        return this.bankName;
    }
    public void setBankName(String bankName){
        this.bankName = bankName;
    }

    @Log
    public String getBankAddress(){
        return this.bankAddress;
    }
    public void setBankAddress(String bankAddress){
        this.bankAddress = bankAddress;
    }








}
