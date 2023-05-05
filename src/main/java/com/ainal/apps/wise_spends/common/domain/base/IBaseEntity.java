package com.ainal.apps.wise_spends.common.domain.base;

import java.util.Date;

public interface IBaseEntity {

    Long getId();
    void setId(Long id);
    
    String getCreatedBy();
    void setCreatedBy(String createdBy);
    
    Date getCreatedDate();
    void setCreatedDate(Date createdDate);
    
    String getLastModifiedBy();
    void setLastModifiedBy(String lastModifiedBy);
    
    Date getLastModifiedDate();
    void setLastModifiedDate(Date lastModifiedDate);
    
    boolean shouldAudit();
}
