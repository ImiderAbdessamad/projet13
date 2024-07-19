package ma.zs.zyn.dao.facade.core.payement;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.payement.PaimentCollaborator;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface PaimentCollaboratorDao extends AbstractRepository<PaimentCollaborator,Long>  {

    @Query("SELECT p FROM PaimentCollaborator p WHERE p.collaborator.username = :username ORDER BY p.paiementDate DESC")
    PaimentCollaborator findLastPaimentCollaborator(@Param("username") String username);

    List<PaimentCollaborator> findByCountryId(Long id);
    int deleteByCountryId(Long id);
    long countByCountryCode(String code);
    List<PaimentCollaborator> findByCityId(Long id);
    int deleteByCityId(Long id);
    long countByCityCode(String code);
    List<PaimentCollaborator> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<PaimentCollaborator> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingCode(String code);
    List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code);
    public int deleteByPaimentCollaboratorStateCode(String code);
    long countByPaimentCollaboratorStateCode(String code);
    List<PaimentCollaborator> findByPaimentCollaboratorTypeCode(String code);
    public int deleteByPaimentCollaboratorTypeCode(String code);
    long countByPaimentCollaboratorTypeCode(String code);
    List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id);
    int deleteByInscriptionCollaboratorId(Long id);
    long countByInscriptionCollaboratorId(Long id);

    @Query("SELECT NEW PaimentCollaborator(item.id,item.cardHolder) FROM PaimentCollaborator item")
    List<PaimentCollaborator> findAllOptimized();

    List<PaimentCollaborator> findByCollaboratorUsername(String username);

}
