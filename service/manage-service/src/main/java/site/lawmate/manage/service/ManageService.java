package site.lawmate.manage.service;

import site.lawmate.manage.domain.dto.UserStatsDto;

import java.util.List;
import java.util.Map;

public interface ManageService {
    void saveUserStats();

    List<UserStatsDto> findAll();

    List<UserStatsDto> findByMonth();

    Map<String, Long> getUserTotalStats();

}
