package com.dmm.stateMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author: zhangxun
 * @create: 2023-07-19  13:39
 * @description: 状态机
 **/
public class StateMachine<TState extends IState> {
  private static final Logger log = Logger.getLogger(StateMachine.class.getName());

  /**
   * 存储状态的map
   * stateCode ---> state
   */
  private final Map<String, StateWrapper<TState>> stateContainer = new HashMap<>();


  private StateWrapper<TState> currentState;

  private boolean running = false;

  public void setStates(List<TState> states) {
    if (null == states) {
      return;
    }
    this.stateContainer.clear();

    for (TState state : states) {
      if (state != null) {
        stateContainer.put(state.getCode(), new StateWrapper<>(state, new StateData()));
      }
    }
  }

  public void start(String stateCode) {
    this.running = true;
    this.currentState = stateContainer.get(stateCode);
    if (currentState == null) {
      throw new IllegalStateException("stateCode is " + stateCode + " can not be found in stateContainer.");
    }
    this.currentState.data.reset();
    this.currentState.state.reset();
  }

  public String getCurrentStateCode() {
    if (currentState == null) {
      return null;
    }
    return this.currentState.state.getCode();
  }

  /**
   * 刷新状态机逻辑
   * 1. 启动
   * 2. 更新
   * 3. 结束
   * 4. 超时
   */
  public void update() {
    if (!running) {
      log.warning("state machine is not started.");
      return;
    }
    StateWrapper<TState> currentStateWrapper = this.currentState;
    if (currentStateWrapper == null) {
      log.warning("The current state is idle/unused.");
      return;
    }

    TState state = currentStateWrapper.state;
    StateData data = currentStateWrapper.data;

    // 判断状态是否启动，如果没启动尝试启动
    if (!state.isStarted()) {
      state.start();
      try {
        state.initialize(data);
      } catch (Exception e) {
        state.onInitializeError(e, data);
      }
    }

    // 如果状态不需要更新，那么就会选择到下一个状态
    if (!data.needUpdate) {
      try {
        String nextStateCode = state.finish(data);
        switchToState(nextStateCode);
      } catch (Exception e) {
        log.warning(state.getCode() +" without update finish exception: " + e.getMessage());
        String nextState = state.onFinishError(false, e, data);
        this.switchToState(nextState);
      }
      return;
    }

    // 执行当前状态需要更新的方法
    try {
      state.update(data);
    } catch (Exception e) {
      state.onUpdateError(e, data);
    }

    if (data.finish) {
      try {
        String nextState = state.finish(data);
        this.switchToState(nextState);
      } catch (Exception e) {
        log.warning(state.getCode() +" without update finish exception: " + e.getMessage());
        String nextState = state.onFinishError(false, e, data);
        this.switchToState(nextState);
      }
      return;
    }

    // 超时
    long timeout = state.getTimeout();
    if (timeout > 0 && System.currentTimeMillis() - state.getStartTime() >= timeout) {
      try {
        log.warning(state.getCode() + " timeout!");
        String nextState = state.finishTimeout(data);
        this.switchToState(nextState);
      } catch (Exception e) {
        log.warning(state.getCode() +" without update finish exception: " + e.getMessage());
        String nextState = state.onFinishError(true, e, data);
        this.switchToState(nextState);
      }
    }
  }

  // 更改当前的状态，并且重置即将需要转换的状态
  public void switchToState(String stateCode) {
    if (stateCode == null || stateCode.equals("")) {
      this.currentState = null;
      return;
    }

    this.currentState = this.stateContainer.get(stateCode);
    if (null == this.currentState) {
      log.warning("Try to switch to invalid state: " + stateCode);
      return;
    }

    this.currentState.data.reset();
    this.currentState.state.reset();
  }
}
