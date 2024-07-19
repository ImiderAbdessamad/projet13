package ma.zs.zyn.dao.facade.core.contactus;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.contactus.ContactUsState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.contactus.ContactUsState;
import java.util.List;


@Repository
public interface ContactUsStateDao extends AbstractRepository<ContactUsState,Long>  {
    ContactUsState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ContactUsState(item.id,item.libelle) FROM ContactUsState item")
    List<ContactUsState> findAllOptimized();

}
