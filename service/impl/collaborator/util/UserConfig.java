package ma.zs.zyn.service.impl.collaborator.util;

/**
 * @author Zouani
 */
public class UserConfig {

    private String yamlText;

    public String getYamlText() {
        return yamlText;
    }

    public void setYamlText(String yamlText) {
        this.yamlText = yamlText;
    }

    public UserConfig(String yamlText) {
        this.yamlText = yamlText;
    }
}
