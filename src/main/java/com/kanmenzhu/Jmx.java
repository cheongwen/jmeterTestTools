package com.kanmenzhu;

import com.sun.prism.shader.DrawPgram_ImagePattern_AlphaTest_Loader;

public class Jmx {

    public final static String NUM_THREADS = "ThreadGroup.num_threads";
    public final static String RAMP_TIME = "ThreadGroup.ramp_time";
    public final static String DURATION = "ThreadGroup.duration";
    public final static String CONTINUE_FOREVER = "LoopController.continue_forever";
    public final static String LOOPS = "LoopController.loops";

    public class ThreadGroup {
        /**
         * 线程数
         */
        private String num_threads;
        /**
         * 间隔启动时间
         */
        private String ramp_time;
        /**
         * 持续运行时间
         */
        private String duration;
        /**
         * 永远循环
         */
        private boolean continue_forever;


    }
}
