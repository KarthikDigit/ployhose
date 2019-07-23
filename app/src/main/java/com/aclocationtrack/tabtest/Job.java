package com.aclocationtrack.tabtest;

import java.io.Serializable;
import java.util.List;

public class Job implements Serializable {

    private String job;
    private String description;

    private List<Material> materialList;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDescription() {
        return description;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }
}
