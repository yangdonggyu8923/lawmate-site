package site.lawmate.manage.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCaseLawDetail is a Querydsl query type for CaseLawDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCaseLawDetail extends EntityPathBase<CaseLawDetail> {

    private static final long serialVersionUID = -1772010321L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCaseLawDetail caseLawDetail = new QCaseLawDetail("caseLawDetail");

    public final QCaseLaw caseLaw;

    public final StringPath caseType = createString("caseType");

    public final StringPath court = createString("court");

    public final StringPath detail = createString("detail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath summary = createString("summary");

    public QCaseLawDetail(String variable) {
        this(CaseLawDetail.class, forVariable(variable), INITS);
    }

    public QCaseLawDetail(Path<? extends CaseLawDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCaseLawDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCaseLawDetail(PathMetadata metadata, PathInits inits) {
        this(CaseLawDetail.class, metadata, inits);
    }

    public QCaseLawDetail(Class<? extends CaseLawDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.caseLaw = inits.isInitialized("caseLaw") ? new QCaseLaw(forProperty("caseLaw")) : null;
    }

}

