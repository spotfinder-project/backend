package com.project.trash.report.controller;

import com.project.trash.common.response.DataResponse;
import com.project.trash.common.response.PageListResponse;
import com.project.trash.common.response.SuccessResponse;
import com.project.trash.report.controller.validation.ReportValidator;
import com.project.trash.report.request.ReportListRequest;
import com.project.trash.report.request.ReportModifyRequest;
import com.project.trash.report.response.ReportDetailResponse;
import com.project.trash.report.response.ReportListResponse;
import com.project.trash.report.service.ReportCommandService;
import com.project.trash.report.service.ReportQueryService;

import org.apache.commons.lang3.tuple.Pair;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Tag(name = "신고")
public class ReportController {

  private final ReportCommandService reportCommandService;
  private final ReportQueryService reportQueryService;

  @GetMapping("/{reportId}")
  @Operation(summary = "신고 상세 조회",
      description = "신고 내용을 상세 조회한다."
          + "\n[에러 코드]"
          + "\n- RPT000 : 신고 정보가 존재하지 않습니다.")
  public DataResponse<ReportDetailResponse> getDetail(@PathVariable Long reportId) {
    return new DataResponse<>(reportQueryService.getDetail(reportId));
  }

  @GetMapping
  @Operation(summary = "신고 목록 조회",
      description = "신고 목록을 조회한다.")
  public PageListResponse<ReportListResponse> getList(@ParameterObject ReportListRequest param) {
    ReportValidator.validate(param);

    Pair<List<ReportListResponse>, Long> pair = reportQueryService.getList(param);
    return new PageListResponse<>(param, pair.getLeft(), pair.getRight());
  }

  @PutMapping
  @Operation(summary = "신고 정보 수정",
      description = "신고 정보를 수정한다."
          + "\n[에러 코드]"
          + "\n- RPT000 : 신고 정보가 존재하지 않습니다.")
  public SuccessResponse put(@RequestBody ReportModifyRequest param) {
    ReportValidator.validate(param);

    reportCommandService.modify(param);
    return new SuccessResponse();
  }
}
