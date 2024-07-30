package site.lawmate.user.service;

import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.dto.IssueDto;
import site.lawmate.user.domain.model.Issue;

public interface IssueService extends CommandService<IssueDto>, QueryService<IssueDto> {
    default Issue dtoToEntity(IssueDto dto) {
        return Issue.builder()
                .law(dto.getLaw())
                .title(dto.getTitle())
                .content(dto.getContent())
                .attachment(dto.getAttachment())
                .build();
    }

    default IssueDto entityToDto(Issue issue) {
        return IssueDto.builder()
                .id(issue.getId())
                .law(issue.getLaw())
                .title(issue.getTitle())
                .content(issue.getContent())
                .attachment(issue.getAttachment())
                .build();
    }

    Messenger update(IssueDto dto);

    Messenger count();
}
