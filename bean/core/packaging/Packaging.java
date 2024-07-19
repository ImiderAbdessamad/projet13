package ma.zs.zyn.bean.core.packaging;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "packaging")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="packaging_seq",sequenceName="packaging_seq",allocationSize=1, initialValue = 1)
public class Packaging  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String description;

    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal maxEntity = BigDecimal.ZERO;

    private BigDecimal maxProjet = BigDecimal.ZERO;

    private BigDecimal maxAttribut = BigDecimal.ZERO;

    private BigDecimal maxTokenInput = BigDecimal.ZERO;

    private BigDecimal maxTokenOutput = BigDecimal.ZERO;



    public Packaging(){
        super();
    }

    public Packaging(Long id){
        this.id = id;
    }

    public Packaging(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Packaging(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="packaging_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public BigDecimal getMaxEntity(){
        return this.maxEntity;
    }
    public void setMaxEntity(BigDecimal maxEntity){
        this.maxEntity = maxEntity;
    }
    public BigDecimal getMaxProjet(){
        return this.maxProjet;
    }
    public void setMaxProjet(BigDecimal maxProjet){
        this.maxProjet = maxProjet;
    }
    public BigDecimal getMaxAttribut(){
        return this.maxAttribut;
    }
    public void setMaxAttribut(BigDecimal maxAttribut){
        this.maxAttribut = maxAttribut;
    }
    public BigDecimal getMaxTokenInput(){
        return this.maxTokenInput;
    }
    public void setMaxTokenInput(BigDecimal maxTokenInput){
        this.maxTokenInput = maxTokenInput;
    }
    public BigDecimal getMaxTokenOutput(){
        return this.maxTokenOutput;
    }
    public void setMaxTokenOutput(BigDecimal maxTokenOutput){
        this.maxTokenOutput = maxTokenOutput;
    }

    @Transient
    public String getLabel() {
        label = code;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packaging packaging = (Packaging) o;
        return id != null && id.equals(packaging.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

