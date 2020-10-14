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

package com.github.huifer.view.redis.boot.ctr;

import com.github.huifer.view.redis.api.RedisKeysOperation;
import com.github.huifer.view.redis.impl.RedisKeysService;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.utils.SingletData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/key")
public class RedisKeysController {
	RedisKeysOperation keysOperation = new RedisKeysService();

	RedisConnectionConfig config = SingletData.getCurrConfig();

	@GetMapping("/info/{key_region}")
	public ResultVO info(@PathVariable("key_region") String keyRegion) {
		return new ResultVO("ok", keysOperation.keys(config, keyRegion), 200);
	}
}
