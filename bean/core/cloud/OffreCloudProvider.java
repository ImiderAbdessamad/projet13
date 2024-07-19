package ma.zs.zyn.bean.core.cloud;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "offre_cloud_provider")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="offre_cloud_provider_seq",sequenceName="offre_cloud_provider_seq",allocationSize=1, initialValue = 1)
public class OffreCloudProvider  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String description;

    private BigDecimal price = BigDecimal.ZERO;

    private CloudProvider cloudProvider ;


    public OffreCloudProvider(){
        super();
    }

    public OffreCloudProvider(Long id){
        this.id = id;
    }

    public OffreCloudProvider(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public OffreCloudProvider(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="offre_cloud_provider_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cloud_provider")
    public CloudProvider getCloudProvider(){
        return this.cloudProvider;
    }
    public void setCloudProvider(CloudProvider cloudProvider){
        this.cloudProvider = cloudProvider;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffreCloudProvider offreCloudProvider = (OffreCloudProvider) o;
        return id != null && id.equals(offreCloudProvider.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

