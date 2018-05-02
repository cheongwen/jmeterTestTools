package com.kanmenzhu;

import com.alibaba.fastjson.JSONArray;

import javax.sound.sampled.TargetDataLine;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class SummaryReport {

//    百度
//    1114
//        0
//        0.0
//        97.5008976660682
//        29
//        1273
//        178.0
//        240.5
//        558.3999999999996
//        9.308388412142683
//        25.843504155978174
//        1.0635560978717715

    private String lable;
    private Integer samples;
    private Integer ko;
    private BigDecimal error;
    private BigDecimal average;
    private Integer min;
    private Integer max;
    private BigDecimal pct90;
    private BigDecimal pct95;
    private BigDecimal pct99;
    private BigDecimal throughput;
    private BigDecimal received;
    private BigDecimal sent;
    private DecimalFormat df = new DecimalFormat("##.###");

    SummaryReport(JSONArray datalist) {
        this.lable = (String) datalist.get(0);
        this.samples = (Integer) datalist.get(1);
        this.ko = (Integer) datalist.get(2);
        this.error = (BigDecimal) datalist.get(3);
        this.average = (BigDecimal) datalist.get(4);
        try {
            this.min = (Integer) datalist.get(5);
            this.max = (Integer) datalist.get(6);
            this.pct90 = (BigDecimal) datalist.get(7);
            this.pct95 = (BigDecimal) datalist.get(8);
            this.pct99 = (BigDecimal) datalist.get(9);
            this.throughput = (BigDecimal) datalist.get(10);
            this.received = (BigDecimal) datalist.get(11);
            this.sent = (BigDecimal) datalist.get(12);
        }catch (ClassCastException e){
            this.pct90 = (BigDecimal) datalist.get(5);
            this.pct95 = (BigDecimal) datalist.get(6);
            this.pct99 = (BigDecimal) datalist.get(7);
            this.throughput = (BigDecimal) datalist.get(8);
            this.received = (BigDecimal) datalist.get(9);
            this.sent = (BigDecimal) datalist.get(10);
            this.min = (Integer) datalist.get(11);
            this.max = (Integer) datalist.get(12);
        }


    }

    public String[] getStringData() {
        String[] strings = new String[13];
        strings[0] = this.lable;
        strings[1] = samples.toString();
        strings[2] = ko.toString();
        strings[3] = df.format(error);
        strings[4] = df.format(average);
        strings[5] = min.toString();
        strings[6] = max.toString();
        strings[7] = df.format(pct90);
        strings[8] = df.format(pct95);
        strings[9] = df.format(pct99);
        strings[10] = df.format(throughput);
        strings[11] = df.format(received);
        strings[12] = df.format(sent);
        return strings;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public Integer getSamples() {
        return samples;
    }

    public void setSamples(Integer samples) {
        this.samples = samples;
    }

    public Integer getKo() {
        return ko;
    }

    public void setKo(Integer ko) {
        this.ko = ko;
    }

    public BigDecimal getError() {
        return error;
    }

    public void setError(BigDecimal error) {
        this.error = error;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public BigDecimal getPct90() {
        return pct90;
    }

    public void setPct90(BigDecimal pct90) {
        this.pct90 = pct90;
    }

    public BigDecimal getPct95() {
        return pct95;
    }

    public void setPct95(BigDecimal pct95) {
        this.pct95 = pct95;
    }

    public BigDecimal getPct99() {
        return pct99;
    }

    public void setPct99(BigDecimal pct99) {
        this.pct99 = pct99;
    }

    public BigDecimal getThroughput() {
        return throughput;
    }

    public void setThroughput(BigDecimal throughput) {
        this.throughput = throughput;
    }

    public BigDecimal getReceived() {
        return received;
    }

    public void setReceived(BigDecimal received) {
        this.received = received;
    }

    public BigDecimal getSent() {
        return sent;
    }

    public void setSent(BigDecimal sent) {
        this.sent = sent;
    }
}
