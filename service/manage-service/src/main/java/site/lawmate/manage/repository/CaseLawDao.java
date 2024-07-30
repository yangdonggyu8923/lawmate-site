package site.lawmate.manage.repository;

import site.lawmate.manage.domain.dto.CaseLawDetailDto;
import site.lawmate.manage.domain.dto.CaseLawDto;
import site.lawmate.manage.domain.dto.SearchCriteriaDto;

import java.util.List;

public interface CaseLawDao {
    List<CaseLawDto> getCaseLawList();

    CaseLawDetailDto getCaseLawDetail(String serialNumber);

    List<CaseLawDto> getCaseLawListByKeyword(SearchCriteriaDto criteria);


}
