package site.lawmate.manage.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserStats is a Querydsl query type for UserStats
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserStats extends EntityPathBase<UserStats> {

    private static final long serialVersionUID = 1450167040L;

    public static final QUserStats userStats = new QUserStats("userStats");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> increaseRate = createNumber("increaseRate", Long.class);

    public final NumberPath<Long> newUserCount = createNumber("newUserCount", Long.class);

    public QUserStats(String variable) {
        super(UserStats.class, forVariable(variable));
    }

    public QUserStats(Path<? extends UserStats> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserStats(PathMetadata metadata) {
        super(UserStats.class, metadata);
    }

}

