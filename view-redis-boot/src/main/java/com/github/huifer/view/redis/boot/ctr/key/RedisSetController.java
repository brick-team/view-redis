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

import com.github.huifer.view.redis.api.RedisSetOperation;
import com.github.huifer.view.redis.impl.RedisSetOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.utils.SingletData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/set")
public class RedisSetController {
	RedisConnectionConfig config = SingletData.getCurrConfig();

	RedisSetOperation setOperation = new RedisSetOperationImpl();

	@PostMapping("/add")
	public ResultVO add(RedisConnectionConfig config, String k, String v) {
		try {
			setOperation.add(config, k, v);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@GetMapping("/get/{key}")
	public ResultVO get(@PathVariable("key") String k) {
		try {
			return new ResultVO("ok", setOperation.get(config, k), 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@PostMapping("/update")
	public ResultVO update(String k, String ov, String nv) {
		try {
			setOperation.update(config, k, ov, nv);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}

	@PostMapping("/del")
	public ResultVO del(String k, String v) {
		try {
			setOperation.del(config, k, v);
			return new ResultVO("ok", true, 200);
		}
		catch (Exception e) {
			return new ResultVO("error", e.getMessage(), 400);
		}
	}
}
