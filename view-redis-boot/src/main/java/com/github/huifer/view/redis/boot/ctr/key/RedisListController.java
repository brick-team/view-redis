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

import java.util.ArrayList;
import java.util.List;

import com.github.huifer.view.redis.api.RedisListOperation;
import com.github.huifer.view.redis.impl.RedisListOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.param.PageParam;
import com.github.huifer.view.redis.model.vo.IndexAndData;
import com.github.huifer.view.redis.model.vo.PageData;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.utils.SingletData;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/list")
public class RedisListController {

	RedisListOperation redisListOperation = new RedisListOperationImpl();

	RedisConnectionConfig config = SingletData.getCurrConfig();

	public static void main(String[] args) {
		PageParam pageParam = new PageParam(1L, 10L, 11L);


		System.out.println(pageParam.getFirstItem());
		System.out.println(pageParam.getLastItem());
		System.out.println(pageParam.getPageTotal());
	}

	@PostMapping("/add/{key}/{value}")
	public ResultVO add(@PathVariable("key") String k, @PathVariable("value") String v) {
		try {
			redisListOperation.add(config, k, v);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@GetMapping("/get/{key}")
	public ResultVO get(@PathVariable("key") String k) {
		try {
			List list = redisListOperation.get(config, k);
			List<IndexAndData> res = new ArrayList<>(list.size());

			for (int i = 0; i < list.size(); i++) {
				IndexAndData indexAndData = new IndexAndData();
				indexAndData.setIndexId(i);
				indexAndData.setData(list.get(i));
				res.add(indexAndData);
			}
			return new ResultVO("ok", res, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@PostMapping("/update")
	public ResultVO update(String k, String ov, String nv) {
		try {
			redisListOperation.update(config, k, ov, nv);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@DeleteMapping("/removeByRow/{key}/{row}")
	public ResultVO removeByRow(@PathVariable("key") String k,@PathVariable("row") int row) {
		try {
			redisListOperation.removeByRow(config, k, row);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@PostMapping("/del")
	public ResultVO del(String k) {
		try {
			redisListOperation.del(config, k);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@GetMapping("/page")
	public ResultVO page(
			String k, long num, long size
	) {


		Long dataSize = redisListOperation.size(config, k);
		PageParam pageParam = new PageParam(num, size, dataSize);
		List list =
				redisListOperation.get(config, k, pageParam.getFirstItem() - 1, pageParam.getLastItem() - 1);
		PageData pageData = new PageData();
		pageData.setPageNumber(pageParam.getNum());
		pageData.setPageSize(pageParam.getSize());
		pageData.setTotal(pageParam.getDataSize());
		pageData.setDataList(list);

		return new ResultVO("ok", pageData, 200);
	}
}
