package com.devteria.profile.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageRequestDTO {
    Integer pageNo = 0;
    Integer pageSize = 10;
    Sort.Direction sort = Sort.Direction.ASC;
    String sortByColumn = "id";
    public Pageable getPageable(PageRequestDTO pageRequestDTO){
        Integer page = Objects.nonNull(pageRequestDTO.getPageNo()) ? pageRequestDTO.getPageNo() : this.pageNo;
        Integer size = Objects.nonNull(pageRequestDTO.getPageSize()) ? pageRequestDTO.getPageSize() : this.pageSize;
        Sort.Direction sort = Objects.nonNull(pageRequestDTO.getSort()) ? pageRequestDTO.getSort() : this.sort;
        String sortByColumn = Objects.nonNull(pageRequestDTO.getSortByColumn()) ? pageRequestDTO.getSortByColumn() : this.sortByColumn;
        PageRequest request = PageRequest.of(page, size, sort, sortByColumn);
        return request;
    }
}
