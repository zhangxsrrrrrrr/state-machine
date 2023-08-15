package com.dmm.stateMachine.machine;

import com.dmm.stateMachine.state.StateType;

import java.util.logging.Logger;

/**
 * @author: zhangxun
 * @create: 2023-08-14 15:08
 * @description:
 **/
public abstract class StateAdapter implements IState {
    private final Logger log = Logger.getLogger(this.getClass().getName());


    public StateAdapter() {
    }

    private boolean started = false;

    public boolean isStarted() {
        return this.started;
    }

    private long startTime = 0;

    @Override
    public long getStartTime() {
        return this.startTime;
    }

    @Override
    public void start() {
        this.started = true;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void reset() {
        this.started = false;
        this.startTime = 0;
    }

    @Override
    public long getTimeout() {
        // 默认没有超时时间
        return -1;
    }

    @Override
    public String finishTimeout(StateData data) {
        return StateType.BIG_ROUND_END_START_CODE;
    }

    /*
    region 默认异常 begin
     */
    @Override
    public void onInitializeError(Exception e, StateData data) {
        data.needUpdate = false;
        log.warning("onInitializeError " + (e == null ? "null exception" : e.getMessage()));
    }

    @Override
    public void onUpdateError(Exception e, StateData data) {
        data.finish = true;
        log.warning("onUpdateError " + (e == null ? "null exception" : e.getMessage()));
    }
    // region 默认异常 end
}
