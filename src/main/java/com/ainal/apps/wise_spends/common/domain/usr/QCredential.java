package com.ainal.apps.wise_spends.common.domain.usr;

import com.ainal.apps.wise_spends.common.domain.base.QBaseEntity;
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
public class QCredential extends EntityPathBase<Credential> {
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

	public final StringPath username = createString("username");

	public final StringPath encryptedPassword = createString("encryptedPassword");

	public final BooleanPath flagUsernameIsEmail = createBoolean("flagUsernameIsEmail");

	public final BooleanPath flagActive = createBoolean("flagActive");

	public final QUser user;

	// constructor
	public QCredential(String variable) {
		this(Credential.class, PathMetadataFactory.forVariable(variable), INITS);
	}

	public QCredential(Path<? extends Credential> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QCredential(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QCredential(PathMetadata metadata, PathInits inits) {
		this(Credential.class, metadata, inits);
	}

	public QCredential(Class<? extends Credential> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
	}
}
