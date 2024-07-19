package ma.zs.zyn.service.impl.collaborator.util;

public class ProcessResultMessage {

    private String content;
    private String value;
    private boolean url;
    private boolean numerical;
    private int code;

    public ProcessResultMessage() {
    }

    public ProcessResultMessage(String content, int code) {
        this.content = content;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isUrl() {
        return url;
    }

    public void setUrl(boolean url) {
        this.url = url;
    }

    public boolean isNumerical() {
        return numerical;
    }

    public void setNumerical(boolean numerical) {
        this.numerical = numerical;
    }
}
