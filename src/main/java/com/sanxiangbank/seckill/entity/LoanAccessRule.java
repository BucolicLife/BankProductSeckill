package com.sanxiangbank.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAccessRule {
    private Timestamp createTime;
    private int yearLimit;
    private int times;
    private int excludeAmount;
    private int excludeTimes;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getYearLimit() {
        return yearLimit;
    }

    public void setYearLimit(int yearLimit) {
        this.yearLimit = yearLimit;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getExcludeAmount() {
        return excludeAmount;
    }

    public void setExcludeAmount(int excludeAmount) {
        this.excludeAmount = excludeAmount;
    }

    public int getExcludeTimes() {
        return excludeTimes;
    }

    public void setExcludeTimes(int excludeTimes) {
        this.excludeTimes = excludeTimes;
    }

    @Override
    public String toString() {
        return "LoanAccessRule{" +
                "createTime=" + createTime +
                ", yearLimit=" + yearLimit +
                ", times=" + times +
                ", excludeAmount=" + excludeAmount +
                ", excludeTimes=" + excludeTimes +
                '}';
    }
}
