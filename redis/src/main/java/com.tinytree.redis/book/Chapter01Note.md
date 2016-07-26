#Redis 数据结构简介

### 1.STRING

|command         |action                                               |           example                  |            remark   |
| :--------:     | :---------------:                                    |       :--------:                   |  :-----:         |
|     GET        | 获取存储在给定键中的值                                 |     _get name_                     |                     |
|     SET        | 设置存储在给定键中的值                                 |   _set name zhengzhong_           |                  |
|     DEL        | 删除存储在给定键中的值                                 |     _del name_                     |                  |
|     INCR       | 将键储存的值加上1                                     |     _incr name_                    |                   |
|     DECR       |  将键储存的值减上1                                    |     _decr name_                    |                   |
|     INCRBY     | 将键储存的值加上整数amount                             |     _incrby name amount_           |                   |
|     DECRBY     | 将键储存的值减去整数amount                             |     _decrby name amount_           |                    |
|     INCRBYFLOAT| 将键储存的值加上浮点数amount                           |     _incrbyfloat name amount_      |                   |
|     APPEND     | 将value值追加到key-name 末尾                          |     _append key-name value_         |                     |
|     GETRANGE   | 获取指定偏移量start到end范围内所有字符                  |    _getrange key-name start end_   |      包括start和end  |
|     SETRANGE   | 将从offset开始的子串设置成value                        |     _setrange key-name offset value_| 用value去覆盖从offset开始的子串 |
|     GETBIT     | 将字节串看做二进制位串,并返回位串中偏移量为offset的二进制值|     _gebit key-name offset_        |                   |
|     SETBIT     | 将字节串看做二进制位串,并将偏移量为offset的二进制值设为value|   _setbit key-name offset value_ |                  |
|     BITCOUNT   | ~~将键储存的值加上浮点数amount~~                         |  _incrbyfloat name amount_       |                  |
|     BITOP      | ~~将键储存的值加上浮点数amount~~                        |   _incrbyfloat name amount_       |                  |

[TOC] 

### 2.LIST

|command        |action                                     | example                                  |          remark         |
| :---------:   | :---------------:                          | :-----------------:                      |   :---------:   |
|     RPUSH     | 将给定的值推入列表的右端                     |  _rpush list-key item_                   |                  |
|     LPUSH     | 将给定的值推入列表的右端                     |  _rpush list-key item \[value ...\]_     |                  |
|     LRANGE    | 获取列表在给定范围上的所有值                 | _lrange list-key start end_              |  包括start 和end |
|     LINDEX    | 获取列表在给定位置上的单个元素               | _lindex list-key 0_                       |                | 
|     LPOP      | 从列表左端弹出一个值,并返回被弹出的值         |_lpop list-key_                            |                |
|     RPOP      | 从列表右端弹出一个值,并返回被弹出的值         |_rpop list-key_                            |                |
|     LTRIM     | 修剪列表,只保留start到end范围内的值          |_ltrim list-key start end_                 | 包括start 和end         |
|     BLPOP     | 从第一个非空列表左端弹出一个值               |_blpop list-key \[list-item ...\] timeout_  | 或者在timeout秒之内阻塞并等待可弹出的元素出现|
|     BRPOP     | 从第一个非空列表左端弹出一个值               |_brpop list-key \[list-item ...\] timeout_  | 或者在timeout秒之内阻塞并等待可弹出的元素出现|
|     RPOPLPUSH | 从soruce-key右端弹出元素推入dest-key左端     |  _rpoplpush source-key dest-key_           | 并且返回该元素               |
|     BRPOPLPUSH| 从soruce-key右端弹出元素推入dest-key左端     |_brpoplpush source-key dest-key timeout_    |并且返回该元素,如果source-key为空那么timeout内阻塞并等待元素出现|

### 3.SET

|command        |action                                 | example                                 |    remark           |
| :----------:  | :-----------------------:             | :------------------:                    |  :---------:          |
|     SADD      | 将元素添加到集合中                      |   _sadd set-key item \[item ...\]_      | 返回添加元素当中原本不存在与set里面的个数|
|     SMEMBERS  | 返回集合包含的所有元素                  |   _smembers set-key item_                |                       |
|     SISMEMBER | 检查给定的元素是否在于集合中             |  _sismember set-key item_                |                       |
|     SREM      | 如果给定的元素存在于集合中,那么移除这个元素|   _srem set-key item \[item ...\]_       |     返回移除的数量       |
|     SCARD     | 返回集合包含的元素个数                   |   _scard set-key_                        |                         |
|   SRANGEMEMBER| 从集合中随机返回一个或者多个元素          |   _srangemember set-key \[count\]_       | count为正数返回元素不重复,负数可能重复   |
|   SPOP        | 随机移除一个元素,并且返回该元素          |   _spop set-key_                          |                             |
|   SMOVE       | 从soruce中移除item添加到dest中          |   _smove source-key dest-key item_       | 如果item存在.如果成功移除那么返回1否者0 |
|   SINTER      | 返set的交集                           |   _sinter key-name \[key-name...\]_        |返回存在所有集合的元素  |
|   SINTERSTORE | 返set的交集,并存在dest-key中           |_sinterstore dest-key key-name \[key-name...\]_| 返回存在所有集合的元素|
|   SUNION      | 返set的并集                           |   _sunion key-name \[key-name...\]_        |   至少存在于一个集合的元素      |
|   SUNIONSTROE | 返set的并集,并存在dest-key中           |_sunionstore dest-key key-name \[key-name...\]_ |  至少存在于一个集合的元素   |
|   SDIFFSTROE  | 返set的差集,并存在dest-key中           |_sdiffstore dest-key key-name \[key-name...\]_ | 存在于第一个集合但是不存在于其他集合的元素|

### 4.ZSET

|command        |action                                               |  example                                             |     remark      |
| :-----------: | :------------------------:                          | :---------------:                                  |:------:           |
|     ZADD      | 将一个代用给定分支的成员添加到有序集合里面               |  _zadd zet-key 705 item zadd zet-key 785 item1_    |                  |
|     ZRANGE    | 根据元素在有序排列中所处的位置,从有序集合里面获取多个元素 |   _zrange zset-key 0 -1 withsscores_                |                 |
| ZRANGEBYSCORE | 获取有序集合在给定分值范围内的所有元素                  |  _zrangbyscore zset-key 0 800 withscotes_           |                  |
|     ZREM      | 如果给定的成员存在有序集合,那么移除这个成员              |  _zrem zset-key item_                               |                  |

### 5.HASH

|command        |action                                | example                                      |   remark       |
| :---------:     |         :------------:             | :---------------:                            |  :---------:   |
|    HSET       | 在散列里面关联起给定的键值对            |   _hset hash-key item zhengzhong_            |                |
|    HGET       |获取指定散列键的值                      | _hget hash-key item_                         |                |
|    HGETALL    |获取散列包含的所有的键值对               |   _hgetall hash-key_                         |                |
|    HDEL       |如果给定的键存在于散列里面,那么移除这个键 | _hdel hash-key key value \[key value ...\]_   |                |
|    HMSET      |在散列里面关联起给定的键值对             |  _hmset hash-key key value \[key value ...\]_|               |
|    HMGET      |获取指定散列多个键的值                  | _hmget hash-key key \[key ...\]_             |                |
|    HLEN       |获取指定散列键值对的数量                |       _hlen hash-key_                         |               |
|    HEXISTS    |检查给定的键是否存在于散列中             |       _hexists hash-key key_                 |               |
|    HKEYS      |获取指定散列所有的键                    |       _hkeys hash-key_                       |               |
|    HVALS      |获取指定散列所有的值                    |       _hvals hash-key_                       |               |
|    HINCRBY    |将键key保存的值加上整数increment        |       _hincrby hash-key key increment_       |               |
|  HINCRBYFLOAT |将键key保存的值加上浮点数increment      |       _hincrbyfloat hash-key key increment_   |               |



- STRING字符串 
- LIST列表 
- SET集合 
- HASH散列 
- ZSET有序集合
~~一般用散列在存储对象 命名空间使用冒号 来分割名字的不同部分 例如 article:82617~~ []( []( []( [](``) ) ) )
