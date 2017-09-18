package com.brubix.entity.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by sanjeev.singh1 on 15/09/17.
 */
@Getter
@Setter
@Embeddable
public class MileStone {

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.DATE)
    private Date deletedAt;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "deleted_by")
    private Integer deletedBy;
}