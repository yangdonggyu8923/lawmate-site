package site.lawmate.manage.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.lawmate.manage.domain.dto.UserStatsDto;
import site.lawmate.manage.domain.model.QUserStats;
import site.lawmate.manage.repository.ManageDao;
//import site.lawmate.user.domain.model.QUser;

import java.time.LocalDate;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ManageDaoImpl implements ManageDao {

    private final JPAQueryFactory factory;
   // private final QUser user = QUser.user;
    private final QUserStats userStats = QUserStats.userStats;
    private final LocalDate yesterday = LocalDate.now().minusDays(1);
//    @Override
//    public Long getYesterdayNewUserCount() {
//        return factory.select(user.count())
//                .from(user)
//                .where(user.regDate.goe(yesterday.atStartOfDay()).and(user.regDate.lt(yesterday.plusDays(1).atStartOfDay())))
//                .fetchOne();
//    }

    @Override
    public Long getYesterdayNewUserCount() {
        return null;
    }

    @Override
    public Long getIncreaseRate() {
        return Math.round(getTotalUserCount() / (double) (getTotalUserCount() - getYesterdayNewUserCount()) * 100);
    }

    @Override
    public Long getTotalUserCount() {
        return null;
    }


//    @Override
//    public Long getTotalUserCount() {
//        return factory.select(user.count())
//                .from(user)
//                .fetchOne();
//    }

    @Override
    public List<UserStatsDto> getUserStatsByMonth() {
        return factory.select(
                Projections.fields(UserStatsDto.class,
                        userStats.date.yearMonth().as("month"),
                        userStats.newUserCount.sum().as("newUserCount"),
                        userStats.increaseRate.avg().round().longValue().as("increaseRateAverage")
        ))
                .from(userStats)
                .groupBy(userStats.date.yearMonth())
                .orderBy(userStats.date.yearMonth().desc())
                .fetch();
    }

    @Override
    public Long getMaleCount() {
        return null;
    }

    @Override
    public Long getFemaleCount() {
        return null;
    }

    @Override
    public Map<String, Long> getUserCountByAgeGroup() {
        return null;
    }

//    @Override
//    public Long getMaleCount() {
//        return factory.select(user.count())
//                .from(user)
//                .where(user.gender.eq("남자"))
//                .fetchOne();
//    }

//    @Override
//    public Long getFemaleCount() {
//        return factory.select(user.count())
//                .from(user)
//                .where(user.gender.eq("여자"))
//                .fetchOne();
//    }

//    @Override
//    public Map<String, Long> getUserCountByAgeGroup() {
//        Map<String, Long> ageGroupMap = new HashMap<>();
//        List<Tuple> ageGroupList = factory.select(user.age, user.count())
//                .from(user)
//                .groupBy(user.age)
//                .fetch();
//
//        ageGroupList.forEach(tuple ->{
//            String ageGroup = tuple.get(user.age);
//            ageGroup = switch (Integer.parseInt(Objects.requireNonNull(ageGroup)) / 10) {
//                case 0, 1 -> "20대 미만";
//                case 2 -> "20대";
//                case 3 -> "30대";
//                case 4 -> "40대";
//                case 5 -> "50대";
//                default -> "60대 이상";
//            };
//            Long count = tuple.get(user.count());
//            ageGroupMap.put(ageGroup, ageGroupMap.getOrDefault(ageGroup, 0L) + count);
//        });
//
//
//        return ageGroupMap;
//    }

}
