package ma.zs.zyn.dao.facade.core.collaborator;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CollaboratorDao extends AbstractRepository<Collaborator,Long>  {

    List<Collaborator> findByCountryId(Long id);
    int deleteByCountryId(Long id);
    long countByCountryCode(String code);
    List<Collaborator> findByCityId(Long id);
    int deleteByCityId(Long id);
    long countByCityCode(String code);
    Collaborator findByUsername(String username);




}
