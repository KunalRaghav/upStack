package com.krsolutions.upstack.api.model;

import java.util.List;

public class tagResponse {
    List<tag> items;
    Boolean has_more;
    Integer quota_max;
    Integer quota_remaining;

    public tagResponse(List<tag> items, Boolean has_more, Integer quota_max, Integer quota_remaining) {
        this.items = items;
        this.has_more = has_more;
        this.quota_max = quota_max;
        this.quota_remaining = quota_remaining;
    }

    public tagResponse() {
    }

    public List<tag> getItems() {
        return items;
    }

    public void setItems(List<tag> items) {
        this.items = items;
    }

    public Boolean getHas_more() {
        return has_more;
    }

    public void setHas_more(Boolean has_more) {
        this.has_more = has_more;
    }

    public Integer getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(Integer quota_max) {
        this.quota_max = quota_max;
    }

    public Integer getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(Integer quota_remaining) {
        this.quota_remaining = quota_remaining;
    }
}
