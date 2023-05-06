package com.ainal.apps.wise_spends.common.domain.ref;

import com.ainal.apps.wise_spends.common.domain.base.QBaseEntity;
import com.ainal.apps.wise_spends.common.domain.usr.QRole;
import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRole extends EntityPathBase<UserRole> {
	private static final long serialVersionUID = 174334787L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QUserRole userRole = new QUserRole("userRole");

	public final QBaseEntity _super = new QBaseEntity("userRole");

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

	public final StringPath name = createString("name");

	public final StringPath code = createString("code");

	public final BooleanPath flagActive = createBoolean("flagActive");

	public final ListPath<Role, QRole> roleList = this.<Role, QRole>createList("roleList", Role.class, QRole.class,
			PathInits.DIRECT2);

	// constructor
	public QUserRole(String variable) {
		this(UserRole.class, PathMetadataFactory.forVariable(variable), INITS);
	}

	public QUserRole(Path<? extends UserRole> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QUserRole(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QUserRole(PathMetadata metadata, PathInits inits) {
		this(UserRole.class, metadata, inits);
	}

	public QUserRole(Class<? extends UserRole> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
	}
}
