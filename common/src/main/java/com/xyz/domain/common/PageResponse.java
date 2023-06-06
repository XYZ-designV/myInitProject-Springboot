package com.xyz.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "PageResponse - 分页统一响应", description = "分页统一响应")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> implements Serializable {
    // 分页数据
    @ApiModelProperty("分页数据")
    private List<T> rows;
    // 数据总条数
    @ApiModelProperty("数据总条数")
    private Long total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public PageResponse(List<T> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "rows=" + rows +
                ", total=" + total +
                '}';
    }
}