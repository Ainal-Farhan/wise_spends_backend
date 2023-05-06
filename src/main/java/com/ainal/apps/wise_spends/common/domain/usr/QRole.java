package com.ainal.apps.wise_spends.common.domain.usr;

import com.ainal.apps.wise_spends.common.domain.base.QBaseEntity;
import com.ainal.apps.wise_spends.common.domain.ref.QUserRole;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {
	private static final long serialVersionUID = 174334787L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QRole role = new QRole("role");

	public final QBaseEntity _super = new QBaseEntity("user");

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

	public final BooleanPath flagActive = createBoolean("flagActive");

	public final BooleanPath flagMainRole = createBoolean("flagMainRole");

	public final QUserRole userRole;

	public final QUser user;

	// constructor
	public QRole(String variable) {
		this(Role.class, PathMetadataFactory.forVariable(variable), INITS);
	}

	public QRole(Path<? extends Role> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QRole(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QRole(PathMetadata metadata, PathInits inits) {
		this(Role.class, metadata, inits);
	}

	public QRole(Class<? extends Role> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.userRole = inits.isInitialized("userRole") ? new QUserRole(forProperty("userRole"), inits.get("userRole"))
				: null;
		this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
	}

}
