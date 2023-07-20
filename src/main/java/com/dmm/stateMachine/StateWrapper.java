package com.dmm.stateMachine;

/**
 * @author: zhangxun
 * @create: 2023-07-19 13:49
 * @description: 这个类里面包含了状态信息以及状态对应的结果
 **/
public class StateWrapper<TState extends IState> {

  public final TState state;

  public final StateData data;

  public StateWrapper(TState state, StateData data) {
    if (state == null) {
      throw new IllegalArgumentException("state should not be null.");
    }
    if (data == null) {
      throw new IllegalArgumentException("data should not be null.");
    }
    this.state = state;
    this.data = data;
  }
}
