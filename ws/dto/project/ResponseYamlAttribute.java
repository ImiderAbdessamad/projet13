package ma.zs.zyn.ws.dto.project;

public class ResponseYamlAttribute {
    private String name;
    private String nameStyle;
    private String type;
    private String tag;

    public ResponseYamlAttribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ResponseYamlAttribute(String name, String type, String tag) {
        this.name = name;
        this.type = type;
        this.tag = tag;
    }

    public ResponseYamlAttribute() {
    }

    public String getNameStyle() {
        return nameStyle;
    }

    public void setNameStyle(String nameStyle) {
        this.nameStyle = nameStyle;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
