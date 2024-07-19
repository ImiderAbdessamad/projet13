package ma.zs.zyn.dao.facade.core.configuration;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.configuration.BankTransferConfiguration;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BankTransferConfigurationDao extends AbstractRepository<BankTransferConfiguration,Long>  {



}
