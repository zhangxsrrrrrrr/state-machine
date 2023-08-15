package com.dmm.stateMachine.machine;


import lombok.extern.slf4j.Slf4j;

/**
 * @author: zhangxun
 * @create: 2023-08-14 15:08
 * @description:
 **/
@Slf4j
public abstract class BaseState implements IState {

    public BaseState() {
    }

    private boolean started = false;

    @Override
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
        // 默认没有超时时间。
        return -1;
    }

    // region 提供默认的出错实现

    @Override
    public void onInitializeError(Exception e, StateData data) {
        data.needUpdate = false;
        log.error("onInitializeError {}", e.getMessage());
    }

    @Override
    public void onUpdateError(Exception e, StateData data) {
        data.finish = true;
        log.error("onUpdateError {}", e.getMessage());
    }

    //endregion
}
