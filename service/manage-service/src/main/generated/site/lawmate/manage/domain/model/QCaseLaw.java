package site.lawmate.manage.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCaseLaw is a Querydsl query type for CaseLaw
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCaseLaw extends EntityPathBase<CaseLaw> {

    private static final long serialVersionUID = -2040609090L;

    public static final QCaseLaw caseLaw = new QCaseLaw("caseLaw");

    public final StringPath caseName = createString("caseName");

    public final StringPath caseNumber = createString("caseNumber");

    public final StringPath dateOfDecision = createString("dateOfDecision");

    public final StringPath serialNumber = createString("serialNumber");

    public QCaseLaw(String variable) {
        super(CaseLaw.class, forVariable(variable));
    }

    public QCaseLaw(Path<? extends CaseLaw> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCaseLaw(PathMetadata metadata) {
        super(CaseLaw.class, metadata);
    }

}

