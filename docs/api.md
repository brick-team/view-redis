# API document
- 本项目 context-path : redis . 
## key 操作

### key 搜索
- 请求方式: post
- 请求地址: `/key/info`
- 请求参数

```json
{
  "key": ""
}
```

- 返回值
    - expire: 过期时间,-1 表示永久有效
    - key: redis 键名称
    - dataType: redis 数据类型
```json
{"msg":"ok","data":[{"key":"1","dataType":"HASH","expire":-1}],"code":200}
```
    
    
### key 删除
- 请求方式: post
- 请求地址: `/key/del`
- 请求参数

```json
{
  "key": ""
}
```

- 返回参数
```json
{"msg":"ok","data":true,"code":200}
```



## String 类型操作

### 添加 string key
- 请求方式: post
- 请求地址: `/string/add`
- 请求参数:

```json
{
"key": "",
"value": ""
}
```

- 返回参数
```json
{"msg":"ok","data":true,"code":200}
```


### 获取 string key
- 请求方式: post
- 请求地址: `/string/get`
- 请求参数:

```json
{
"key": ""
}
```



### 删除 string key
- 请求方式: post
- 请求地址: `/string/delete`
- 请求参数:

```json
{
"key": ""
}
```



## Set 类型操作

### 添加 set key
- 请求方式: post
- 请求地址: `/set/add` 
- 请求参数:
```json
{
"key": "",
"value": ""  
}
```
- 返回值:

### 获取 set key
- 请求方式: post
- 请求地址: `/set/get`
- 请求参数:
```json
{
"key": ""
}
```
- 返回值:

### 更新 set key
- 请求方式: post
- 请求地址: `/set/update`
- 请求参数:
```json
{
"key": "",
"nv": "",
"ov": ""
}
```
- 返回值:

### 删除 set key 
- 请求方式: post
- 请求地址: `/set/delete`
- 请求参数:
```json
{
"key": "",
"value": ""
}
```

- 返回值: