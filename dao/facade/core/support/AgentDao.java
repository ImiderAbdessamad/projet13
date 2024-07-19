package ma.zs.zyn.dao.facade.core.support;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.support.Agent;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AgentDao extends AbstractRepository<Agent,Long>  {

    Agent findByUsername(String username);

}
