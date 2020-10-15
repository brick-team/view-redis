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

package com.github.huifer.view.redis.model.param;

public class PageParam {
	/**
	 * 页面号
	 */
	private Long num;

	/**
	 * 分页数量
	 */
	private Long size;

	/**
	 * 开始行数
	 */
	private Long firstItem;

	/**
	 * 结束行数
	 */
	private Long lastItem;

	/**
	 * 页面数量
	 */
	private Long pageTotal;

	/**
	 * 总共数据数量
	 */
	private Long dataSize;

	public PageParam(Long num, Long size, Long dataSize) {
		this.num = num;
		this.size = size;
		Long firstItem = size * (num - 1) + 1;
		Long lastItem = size * num;
		this.firstItem = firstItem;
		this.lastItem = lastItem;
		this.dataSize = dataSize;
		if (dataSize / size <= 0) {
			setPageTotal(1L);
		}
		else {
			if (dataSize % size == 0) {
				setPageTotal(dataSize / size);
			}
			else {
				setPageTotal((dataSize / size) + 1);
			}
		}

	}

	public Long getDataSize() {
		return dataSize;
	}

	public void setDataSize(Long dataSize) {
		this.dataSize = dataSize;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(Long firstItem) {
		this.firstItem = firstItem;
	}

	public Long getLastItem() {
		return lastItem;
	}

	public void setLastItem(Long lastItem) {
		this.lastItem = lastItem;
	}

	public Long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}
}
