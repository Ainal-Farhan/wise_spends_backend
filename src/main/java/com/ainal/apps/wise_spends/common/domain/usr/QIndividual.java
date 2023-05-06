package com.ainal.apps.wise_spends.common.domain.usr;

import com.ainal.apps.wise_spends.common.domain.base.QBaseEntity;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QIndividual extends EntityPathBase<Individual> {
	private static final long serialVersionUID = 174334787L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QIndividual individual = new QIndividual("individual");

	public final QBaseEntity _super = new QBaseEntity("individual");

	// inherited
	public final StringPath createdBy = _super.createdBy;

	// inherited
	public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

	// inherited
	public final StringPath lastModifiedBy = _super.lastModifiedBy;

	// inherited
	public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

	// inherited
	public final NumberPath<Long> version = _super.version;

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath firstName = createString("firstName");

	public final StringPath lastName = createString("lastName");

	public final StringPath email = createString("email");

	public final StringPath nickName = createString("nickName");

	public final StringPath gender = createString("gender");

	public final QUser user;

	// constructor
	public QIndividual(String variable) {
		this(Individual.class, PathMetadataFactory.forVariable(variable), INITS);
	}

	public QIndividual(Path<? extends Individual> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QIndividual(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QIndividual(PathMetadata metadata, PathInits inits) {
		this(Individual.class, metadata, inits);
	}

	public QIndividual(Class<? extends Individual> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
	}

}
