package com.project.trash.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.trash.common.request.PageRequest;

import org.apache.commons.lang3.math.NumberUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pagination {

  @Schema(description = "총 데이터 개수", example = "30")
  private Long totalItems;
  @Schema(description = "총 페이지 개수", example = "2")
  private Integer totalPages;
  @Schema(description = "현재 페이지 번호", example = "1")
  private Integer currentPage;
  @Schema(description = "다음 페이지 존재 유무", example = "true")
  private Boolean hasNext;
  @Schema(description = "이전 페이지 존재 유무", example = "false")
  private Boolean hasPrevious;
  @Schema(description = "현재 페이지가 첫번째 페이지인지에 대한 여부", example = "true")
  private Boolean isFirst;
  @Schema(description = "현재 페이지가 마지막 페이지인지에 대한 여부", example = "false")
  private Boolean isLast;

  public Pagination(PageRequest param, long totalItems) {
    if (totalItems > 0) {
      this.totalItems = totalItems;
      calculation(param);
    }
  }

  private void calculation(PageRequest param) {
    int size = param.getSize();
    int page = param.getPage();

    this.currentPage = page;

    this.hasNext = NumberUtils.compare(totalItems - ((long) size * page), 0) > 0;
    this.hasPrevious = NumberUtils.compare(page, 1) > 0;

    this.isFirst = NumberUtils.compare(page, 1) == 0;
    this.isLast = NumberUtils.compare(totalItems, (long) size * page) == 0 ||
        NumberUtils.compare(totalItems, (long) size * page) < 0;

    this.totalPages = (int) Math.ceil((totalItems * 1.0d) / (size * 1.d));
  }
}
