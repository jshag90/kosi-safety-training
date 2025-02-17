package com.kosi.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DataTablesRequest {
    String searchField;
    String searchWord;
    int pg;
    int pgSize;

    public int getOffset(){
        return (this.pg-1)*this.pgSize;
    }
}
