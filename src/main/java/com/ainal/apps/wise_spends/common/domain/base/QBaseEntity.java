package com.ainal.apps.wise_spends.common.domain.base;

import java.util.Date;

import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

	private static final long serialVersionUID = -2138815385L;

	public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

	public final StringPath createdBy;

	public final DateTimePath<Date> createdDate;

	public final StringPath lastModifiedBy;

	public final DateTimePath<Date> lastModifiedDate;

	public final NumberPath<Long> version;

	public QBaseEntity(String variable) {
		super(BaseEntity.class, PathMetadataFactory.forVariable(variable));
		createdBy = createString("createdBy");
		createdDate = createDateTime("createdDate", Date.class);
		lastModifiedBy = createString("lastModifiedBy");
		lastModifiedDate = createDateTime("lastModifiedDate", Date.class);
		version = createNumber("version", Long.class);
	}
}
