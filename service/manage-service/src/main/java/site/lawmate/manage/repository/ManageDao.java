package site.lawmate.manage.repository;

import site.lawmate.manage.domain.dto.UserStatsDto;

import java.util.List;
import java.util.Map;

public interface ManageDao{

    Long getYesterdayNewUserCount();
    Long getIncreaseRate();
    Long getTotalUserCount();
    List<UserStatsDto> getUserStatsByMonth();

    Long getMaleCount();
    Long getFemaleCount();

    Map<String, Long> getUserCountByAgeGroup();

}
