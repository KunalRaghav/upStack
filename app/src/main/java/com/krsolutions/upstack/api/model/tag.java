package com.krsolutions.upstack.api.model;

public class tag {
    String name;
    Boolean has_synonyms;
    Boolean is_moderator_only;
    Boolean is_required;
    Integer count;

    public tag(String name, Boolean has_synonyms, Boolean is_moderator_only, Boolean is_required, Integer count) {
        this.name = name;
        this.has_synonyms = has_synonyms;
        this.is_moderator_only = is_moderator_only;
        this.is_required = is_required;
        this.count = count;
    }

    public tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHas_synonyms() {
        return has_synonyms;
    }

    public void setHas_synonyms(Boolean has_synonyms) {
        this.has_synonyms = has_synonyms;
    }

    public Boolean getIs_moderator_only() {
        return is_moderator_only;
    }

    public void setIs_moderator_only(Boolean is_moderator_only) {
        this.is_moderator_only = is_moderator_only;
    }

    public Boolean getIs_required() {
        return is_required;
    }

    public void setIs_required(Boolean is_required) {
        this.is_required = is_required;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
