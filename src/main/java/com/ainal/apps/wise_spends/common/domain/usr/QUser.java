package com.ainal.apps.wise_spends.common.domain.usr;

import com.ainal.apps.wise_spends.common.domain.base.QBaseEntity;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.mny.QMoneyStorage;
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
public class QUser extends EntityPathBase<User> {
	private static final long serialVersionUID = 174334787L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QUser user = new QUser("user");

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

	public final QIndividual individual;

	public final QCredential credential;

	public final ListPath<Role, QRole> roleList = this.<Role, QRole>createList("roleList", Role.class, QRole.class,
			PathInits.DIRECT2);

	public final ListPath<MoneyStorage, QMoneyStorage> moneyStorageList = this.<MoneyStorage, QMoneyStorage>createList(
			"moneyStorageList", MoneyStorage.class, QMoneyStorage.class, PathInits.DIRECT2);

	// constructor
	public QUser(String variable) {
		this(User.class, PathMetadataFactory.forVariable(variable), INITS);
	}

	public QUser(Path<? extends User> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QUser(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QUser(PathMetadata metadata, PathInits inits) {
		this(User.class, metadata, inits);
	}

	public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.individual = inits.isInitialized("individual")
				? new QIndividual(forProperty("individual"), inits.get("individual"))
				: null;
		this.credential = inits.isInitialized("credential")
				? new QCredential(forProperty("credential"), inits.get("credential"))
				: null;
	}
}
