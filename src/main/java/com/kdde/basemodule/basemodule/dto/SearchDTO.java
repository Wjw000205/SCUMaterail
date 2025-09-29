package com.kdde.basemodule.basemodule.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zxx
 * @Date 2025/5/24 17:22
 */
@Data
public class SearchDTO {
    private String select1;
    private String select2;
    private String select3;
    private List<String> select4;

}
