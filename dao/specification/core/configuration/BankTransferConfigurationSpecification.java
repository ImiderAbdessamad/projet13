package ma.zs.zyn.dao.specification.core.configuration;

import ma.zs.zyn.dao.criteria.core.configuration.BankTransferConfigurationCriteria;
import ma.zs.zyn.bean.core.configuration.BankTransferConfiguration;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class BankTransferConfigurationSpecification extends  AbstractSpecification<BankTransferConfigurationCriteria, BankTransferConfiguration>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("iban", criteria.getIban(),criteria.getIbanLike());
        addPredicate("swift", criteria.getSwift(),criteria.getSwiftLike());
        addPredicate("accountHolderName", criteria.getAccountHolderName(),criteria.getAccountHolderNameLike());
        addPredicate("bankName", criteria.getBankName(),criteria.getBankNameLike());
        addPredicate("bankAddress", criteria.getBankAddress(),criteria.getBankAddressLike());
    }

    public BankTransferConfigurationSpecification(BankTransferConfigurationCriteria criteria) {
        super(criteria);
    }

    public BankTransferConfigurationSpecification(BankTransferConfigurationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
