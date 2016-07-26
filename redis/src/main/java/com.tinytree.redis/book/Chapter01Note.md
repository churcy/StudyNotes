#Redis 数据结构简介
STRING字符串 LIST列表 SET集合 HASH散列 ZSET有序集合
一般用散列在存储对象 命名空间使用冒号 来分割名字的不同部分 例如 article:82617

          ***STRING***

**command*************action******************
     GET       * 获取存储在给定键中的值                         get name
     SET       * 设置存储在给定键中的值                         set name zhengzhong
     DEL       * 删除存储在给定键中的值                         del name
**********************************************
          ****LIST****

**command*************action******************
     RPUSH     * 将给定的值推入列表的右端                        rpush list-key item
     LRANGE    * 获取列表在给定范围上的所有值
     LINDEX    * 获取列表在给定位置上的单个元素
     LPOP      * 从列表左端弹出一个值,并返回被弹出的值
**********************************************
          ****SET****

**command*************action******************
     SADD      * 将元素添加到集合中                              sadd set-key item
     SMEMBERS  * 返回集合包含的所有元素
     SISMEMBER * 检查给定的元素是否在于集合中
     SREM      * 如果给定的元素存在于集合中,那么移除这个元素
**********************************************
          ****ZSET****

**command*************action******************
     ZADD      * 将一个代用给定分支的成员添加到有序集合里面                       zadd zet-key 705 item zadd zet-key 785 item1
     ZRANGE    * 根据元素在有序排列中所处的位置,从有序集合里面获取多个元素          zrange zset-key 0 -1 withsscores
 ZRANGEBYSCORE * 获取有序集合在给定分值范围内的所有元素                           zrangbyscore zset-key 0 800 withscotes
     ZREM      * 如果给定的成员存在有序集合,那么移除这个成员                      zrem zset-key item
**********************************************
          ****HASH****

**command*************action******************
     HSET      * 在散列里面关联起给定的键值对                             hset hash-key item zhengzhong
     HGET      * 获取指定散列键的值                                      hget hash-key item
     HGETALL   * 获取散列包含的所有的键值对                               hgetall hash-key
     HDEL      * 如果给定的键存在于散列里面,那么移除这个键                  hdel hash-key item
**********************************************