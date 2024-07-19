package ma.zs.zyn.ws.dto.project;

import java.util.ArrayList;
import java.util.List;

public class ResponseYamlElement {
    private String entityName;
    private String entityStyle;
    private String html;
    private List<ResponseYamlAttribute> attributes;

    public ResponseYamlElement(String entityName) {
        this.entityName = entityName;
        attributes = new ArrayList<>();
    }

    public ResponseYamlElement() {
    }

    public String getEntityName() {
        return entityName;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityStyle() {
        return entityStyle;
    }

    public void setEntityStyle(String entityStyle) {
        this.entityStyle = entityStyle;
    }

    public List<ResponseYamlAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ResponseYamlAttribute> attributes) {
        this.attributes = attributes;
    }

}
