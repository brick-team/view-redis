/*
 *
 * Copyright 2020 HuiFer All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.huifer.view.redis.model.vo;

import java.util.Collection;

public class PageData {
	/**
	 * 当前页
	 */
	private Long pageNumber;

	/**
	 * 分页大小
	 */
	private Long pageSize;

	/**
	 * 总数
	 */
	private Long total;

	/**
	 * 数量
	 */
	private Collection dataList;

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Collection getDataList() {
		return dataList;
	}

	public void setDataList(Collection dataList) {
		this.dataList = dataList;
	}
}
