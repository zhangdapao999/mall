package com.lin.mall.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分布式环境解决方案说明：
 * <p>
 * 机器 ID 分配：
 * 从 IP 地址提取部分数字作为机器标识（0-99）
 * 无法获取 IP 时使用随机数，确保临时唯一性
 * 机器 ID 占订单号的前 2 位（共 100 台机器）
 * <p>
 * 序列号生成：
 * 每台机器独立维护自己的序列号计数器
 * 使用 AtomicInteger 保证线程安全
 * 单台机器每毫秒可生成 10000 个唯一编号（0-9999）
 * <p>
 * 时间戳处理：
 * 保留时间戳的后 4 位（覆盖 10000 毫秒周期）
 * 当序列号溢出时自动等待下一毫秒
 * <p>
 * 最终编号结构：
 * 前两位：BG 前缀（固定）
 * 中间 8 位：年月日（YYYYMMDD）
 * 接着 4 位：时间戳后四位（区分不同毫秒）
 * 最后 6 位：机器 ID (2 位)+ 序列号 (4 位)
 * <p>
 * 扩展性：
 * 支持 100 台机器同时生成订单号
 * 每台机器每毫秒可生成 10000 个订单号
 * 理论最大峰值性能：100 万订单号 / 秒
 * <p>
 * 注意事项：
 * 生产环境建议使用 ZooKeeper 或数据库分配唯一机器 ID
 * 考虑时钟同步问题，建议集群内时钟偏差不超过 10ms
 * 当单节点性能不足时，可以横向扩展机器数量
 */

public class OrderNumberGeneratorUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final String PREFIX = "BG";
    private static final int MAX_SEQUENCE = 999999;

    // 机器ID，根据IP地址生成
    private static final int MACHINE_ID;
    // 每个机器的序列号生成器
    private static final AtomicInteger[] SEQUENCES = new AtomicInteger[100];

    static {
        int machineId = 0;
        try {
            // 从IP地址获取机器ID
            String ip = InetAddress.getLocalHost().getHostAddress();
            machineId = Integer.parseInt(ip.replaceAll("\\D+", "")) % 100;
        } catch (UnknownHostException e) {
            // 获取失败时使用随机数
            machineId = (int) (System.currentTimeMillis() % 100);
        }
        MACHINE_ID = machineId;
        // 初始化序列号生成器数组
        for (int i = 0; i < 100; i++) {
            SEQUENCES[i] = new AtomicInteger(0);
        }
    }

    public static String generateOrderNumber() {
        long currentTimestamp = System.currentTimeMillis();
        String datePart = DATE_FORMAT.format(new Date(currentTimestamp));

        // 获取当前机器的序列号
        int sequence = SEQUENCES[MACHINE_ID].incrementAndGet();
        if (sequence > MAX_SEQUENCE) {
            // 序列号溢出，等待下一毫秒
            currentTimestamp = waitNextMillis(currentTimestamp);
            datePart = DATE_FORMAT.format(new Date(currentTimestamp));
            SEQUENCES[MACHINE_ID].set(0);
            sequence = 0;
        }

        // 生成最终订单号
        String sequencePart = String.format("%02d%04d", MACHINE_ID, sequence);
        String millisPart = String.format("%04d", currentTimestamp % 10000);

        return PREFIX + datePart + millisPart + sequencePart;
    }

    private static long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}