package ma.zs.zyn.dao.facade.core.contactus;

import ma.zs.zyn.bean.core.contactus.ContactUs;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ContactUsDao extends AbstractRepository<ContactUs,Long> {

    List<ContactUs> findByContactUsCategoryId(Long id);
    int deleteByContactUsCategoryId(Long id);
    long countByContactUsCategoryCode(String code);
    List<ContactUs> findByContactUsStateCode(String code);
    public int deleteByContactUsStateCode(String code);
    long countByContactUsStateCode(String code);


}
