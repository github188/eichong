package com.wanma.ims.common.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchResultDTO<T> extends BaseResultDTO {
    private static final long serialVersionUID = -6806586439895084001L;
    private Map<Long, String> faileds = new HashMap<>();
    private List<T> module;

    public BatchResultDTO() {
    }

    public BatchResultDTO(boolean success, String resultCode, String errorDetail) {
        this.success = success;
        this.resultCode = resultCode;
        this.errorDetail = errorDetail;
    }

    public List<T> getModule() {
        return this.module;
    }

    public void setModule(List<T> module) {
        this.module = module;
    }

    public boolean isExist() {
        return (this.module != null) && (this.module.size() > 0);
    }

    public void addFailed(Long id, String errorCode) {
        setResultCode(errorCode);
        this.faileds.put(id, errorCode);
    }

    public Map<Long, String> getFaileds() {
        return this.faileds;
    }
}
