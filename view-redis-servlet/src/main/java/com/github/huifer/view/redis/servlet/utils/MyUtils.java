/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
 */

package com.github.huifer.view.redis.servlet.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;


/**
 * 搬运: druid 读取resource文件方式
 */
public class MyUtils {

	public final static int DEFAULT_BUFFER_SIZE = 1024 * 4;

	public static byte[] readByteArrayFromResource(String resource) throws IOException {
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
			if (in == null) {
				return null;
			}

			return readByteArray(in);
		}
		finally {
			try {
				in.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] readByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static long copy(InputStream input, OutputStream output) throws IOException {
		final int EOF = -1;

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

		long count = 0;
		int n = 0;
		while (EOF != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	public static String readFromResource(String resource) {
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
			if (in == null) {
				in = MyUtils.class.getResourceAsStream(resource);
			}

			if (in == null) {
				return null;
			}

			String text = MyUtils.read(in);
			return text;
		}
		finally {
			try {
				in.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String read(InputStream in) {
		InputStreamReader reader;
		reader = new InputStreamReader(in, StandardCharsets.UTF_8);
		return read(reader);
	}

	public static String read(Reader reader) {
		try {

			StringWriter writer = new StringWriter();

			char[] buffer = new char[DEFAULT_BUFFER_SIZE];
			int n = 0;
			while (-1 != (n = reader.read(buffer))) {
				writer.write(buffer, 0, n);
			}

			return writer.toString();
		}
		catch (IOException ex) {
			throw new IllegalStateException("read error", ex);
		}
	}
}
