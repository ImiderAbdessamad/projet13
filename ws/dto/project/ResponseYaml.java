package ma.zs.zyn.ws.dto.project;

import java.util.ArrayList;
import java.util.List;

public class ResponseYaml {
    private String html;
    private List<ResponseYamlElement> responseYamlElements;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public ResponseYaml() {
        this.responseYamlElements = new ArrayList<>();
    }



    public List<ResponseYamlElement> getResponseYamlElements() {
        return responseYamlElements;
    }

    public void setResponseYamlElements(List<ResponseYamlElement> responseYamlElements) {
        this.responseYamlElements = responseYamlElements;
    }

    @Override
    public String toString() {
        return "ResponseYaml{" +
                "responseYamlElements=" + responseYamlElements +
                '}';
    }
}
