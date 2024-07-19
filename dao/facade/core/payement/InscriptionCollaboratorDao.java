package ma.zs.zyn.dao.facade.core.payement;

import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InscriptionCollaboratorDao extends AbstractRepository<InscriptionCollaborator, Long> {

    InscriptionCollaborator findByCollaboratorId(Long id);

    int deleteByCollaboratorId(Long id);

    long countByCollaboratorId(Long id);

    List<InscriptionCollaborator> findByPackagingId(Long id);

    int deleteByPackagingId(Long id);

    long countByPackagingCode(String code);


    InscriptionCollaborator findByCollaboratorUsername(String username);
}
