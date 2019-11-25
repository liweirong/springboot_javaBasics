package com.bloomFilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author iris
 * @date 2019/11/24
 * 因为要判断数据库的值是否存在，所以第一步是加载数据库所有的数据。在去 Redis 查询之前，先在布隆过滤器查询，
 * 如果 bf 说没有，那数据库肯定没有，也不用去查了。 如果 bf 说有，才走之前的流程。
 * <p>
 * 布隆过滤器解决的问题是什么？如何在海量元素中快速判断一个元素是否存在。所 以除了解决缓存穿透的问题之外，
 * 我们还有很多其他的用途。
 * 比如爬数据的爬虫，爬过的 url 我们不需要重复爬，那么在几十亿的 url 里面，怎么 判断一个 url 是不是已经爬过了？
 * 还有我们的邮箱服务器，发送垃圾邮件的账号我们把它们叫做 spamer，在这么多的
 * 邮箱账号里面，怎么判断一个账号是不是 spamer 等等一些场景，我们都可以用到布隆 过滤器
 */
public class BloomFilterDemo {

    public static Integer insertions = 5; // FYI, for 3%, we always get 5 hash

    public static void main(String[] args) {
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions);

        String data = "test";
        bf.put(data);
        if(bf.mightContain(data)){
            System.out.println("yes");
        }

    }
}
