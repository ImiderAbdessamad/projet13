package  ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.YamlFileCriteria;
import ma.zs.zyn.bean.core.project.YamlFile;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class YamlFileSpecification extends  AbstractSpecification<YamlFileCriteria, YamlFile>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("title", criteria.getTitle(),criteria.getTitleLike());
        addPredicateFk("project","id", criteria.getProject()==null?null:criteria.getProject().getId());
        addPredicateFk("project","id", criteria.getProjects());
    }

    public YamlFileSpecification(YamlFileCriteria criteria) {
        super(criteria);
    }

    public YamlFileSpecification(YamlFileCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
