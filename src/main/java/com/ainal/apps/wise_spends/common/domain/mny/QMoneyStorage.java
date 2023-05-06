package com.ainal.apps.wise_spends.common.domain.mny;

import com.ainal.apps.wise_spends.common.domain.base.QBaseEntity;
import com.ainal.apps.wise_spends.common.domain.usr.QUser;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoneyStorage extends EntityPathBase<MoneyStorage> {
	private static final long serialVersionUID = 174334787L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QMoneyStorage moneyStorage = new QMoneyStorage("moneyStorage");

	public final QBaseEntity _super = new QBaseEntity("moneyStorage");

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

	public final StringPath fullName = createString("fullName");

	public final StringPath abbreviation = createString("abbreviation");

	public final StringPath type = createString("type");

	public final QUser user;

	public final ListPath<Saving, QSaving> savingList = this.<Saving, QSaving>createList("savingList", Saving.class,
			QSaving.class, PathInits.DIRECT2);

	// constructor
	public QMoneyStorage(String variable) {
		this(MoneyStorage.class, PathMetadataFactory.forVariable(variable), INITS);
	}

	public QMoneyStorage(Path<? extends MoneyStorage> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QMoneyStorage(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QMoneyStorage(PathMetadata metadata, PathInits inits) {
		this(MoneyStorage.class, metadata, inits);
	}

	public QMoneyStorage(Class<? extends MoneyStorage> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
	}
}
