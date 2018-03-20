package com.kanmenzhu;

import com.sun.prism.shader.DrawPgram_ImagePattern_AlphaTest_Loader;

public interface Jmx {

    /**
     * 并发数，整数>0
     */
    public final static String NUM_THREADS = "ThreadGroup.num_threads";
    /**
     * 设置启动时间，单位：秒
     */
    public final static String RAMP_TIME = "ThreadGroup.ramp_time";
    /**
     * 按时间执行，设置运行时间，单位：秒
     */
    public final static String DURATION = "ThreadGroup.duration";
    /**
     * 按时间or次数运行，按照时间运行设置true，按照次数运行设置false
     */
    public final static String CONTINUE_FOREVER = "LoopController.continue_forever";
    /**
     * 按次执行，循环次数，整数>0
     */
    public final static String LOOPS = "LoopController.loops";
    /**
     * 开启调度器
     */
    public final static String SCHEDULER = "ThreadGroup.scheduler";


}
