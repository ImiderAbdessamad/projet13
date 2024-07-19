package ma.zs.zyn.dao.facade.core.payement;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.payement.PaimentCollaboratorType;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaimentCollaboratorTypeDao extends AbstractRepository<PaimentCollaboratorType,Long>  {
    PaimentCollaboratorType findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW PaimentCollaboratorType(item.id,item.libelle) FROM PaimentCollaboratorType item")
    List<PaimentCollaboratorType> findAllOptimized();

}
