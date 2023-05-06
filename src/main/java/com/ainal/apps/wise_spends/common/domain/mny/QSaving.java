package com.ainal.apps.wise_spends.common.domain.mny;

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
public class QSaving extends EntityPathBase<Saving> {
	private static final long serialVersionUID = 174334787L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QSaving saving = new QSaving("saving");

	public final QBaseEntity _super = new QBaseEntity("saving");

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

	public final StringPath shortName = createString("shortName");

	public final BooleanPath flagGoal = createBoolean("flagGoal");

	public final NumberPath<java.math.BigDecimal> currentAmount = createNumber("currentAmount",
			java.math.BigDecimal.class);

	public final QMoneyStorage moneyStorage;

	// constructor
	public QSaving(String variable) {
		this(Saving.class, PathMetadataFactory.forVariable(variable), INITS);
	}

	public QSaving(Path<? extends Saving> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QSaving(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QSaving(PathMetadata metadata, PathInits inits) {
		this(Saving.class, metadata, inits);
	}

	public QSaving(Class<? extends Saving> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.moneyStorage = inits.isInitialized("moneyStorage")
				? new QMoneyStorage(forProperty("moneyStorage"), inits.get("moneyStorage"))
				: null;
	}
}
