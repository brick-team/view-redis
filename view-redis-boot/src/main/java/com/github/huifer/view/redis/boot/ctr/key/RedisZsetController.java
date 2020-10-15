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

package com.github.huifer.view.redis.boot.ctr.key;

import java.util.Set;

import com.github.huifer.view.redis.api.RedisZSetOperation;
import com.github.huifer.view.redis.impl.RedisZSetOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.param.PageParam;
import com.github.huifer.view.redis.model.vo.PageData;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.utils.SingletData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/zset")
public class RedisZsetController {

	RedisZSetOperation zSetOperation = new RedisZSetOperationImpl();

	RedisConnectionConfig config = SingletData.getCurrConfig();

	@PostMapping("/add")
	public ResultVO add(String k, double score, String member) {
		try {
			zSetOperation.add(config, k, score, member);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@PostMapping("/del")
	public ResultVO del(String key, String member) {
		try {
			zSetOperation.del(config, key, member);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@GetMapping("/get/{key}")
	public ResultVO get(@PathVariable("key") String key) {
		try {
			return new ResultVO("ok", zSetOperation.get(config, key), 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@PostMapping("/update")
	public ResultVO update(String k, double score, String member) {
		try {
			zSetOperation.update(config, k, score, member);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}


	@GetMapping("/page")
	public ResultVO page(String k, long num, long size
	) {
		Long dataSize = zSetOperation.size(config, k);
		PageParam pageParam = new PageParam(num, size, dataSize);
		Set list =
				zSetOperation.get(config, k, pageParam.getFirstItem() - 1, pageParam.getLastItem() - 1);
		PageData pageData = new PageData();
		pageData.setPageNumber(pageParam.getNum());
		pageData.setPageSize(pageParam.getSize());
		pageData.setTotal(pageParam.getDataSize());
		pageData.setDataList(list);

		return new ResultVO("ok", pageData, 200);
	}
}
