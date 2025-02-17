package com.kosi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListResp<T> {

	private List<T> list;
	protected int total;
	protected int currPg;
	protected int lastPg;

	public ListResp(int pg) {
		this.currPg = pg;
	}

	public ListResp(int total, List<T> list) {
		this.total = total;
		this.list = list;
	}

	@Override
	public String toString() {
		return "ListResp [list=" + list + ", total=" + total + ", currPg=" + currPg + ", lastPg=" + lastPg + "]";
	}

}
