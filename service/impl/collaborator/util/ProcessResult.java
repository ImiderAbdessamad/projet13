package ma.zs.zyn.service.impl.collaborator.util;

import java.util.ArrayList;
import java.util.List;

public class ProcessResult {
    private List<ProcessResultMessage> errors = new ArrayList<>();
    private List<ProcessResultMessage> infos = new ArrayList<>();
    private int code;
    private Long yamlFileId;

    public ProcessResult(int code) {
        this.code = code;
    }

    public ProcessResult() {
    }

    public ProcessResult(List<ProcessResultMessage> errors) {
        this.errors = errors;
    }

    public ProcessResult(List<ProcessResultMessage> errors, List<ProcessResultMessage> infos) {
        this.errors = errors;
        this.infos = infos;
    }

    public List<ProcessResultMessage> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getYamlFileId() {
        return yamlFileId;
    }

    public void setYamlFileId(Long yamlFileId) {
        this.yamlFileId = yamlFileId;
    }

    public void setErrors(List<ProcessResultMessage> errors) {
        this.errors = errors;
    }

    public List<ProcessResultMessage> getInfos() {
        if (infos == null) {
            infos = new ArrayList<>();
        }
        return infos;
    }


    public void setInfos(List<ProcessResultMessage> infos) {
        this.infos = infos;
    }
}
