package com.dmm.stateMachine.machine;

/**
 * @author: zhangxun
 * @create: 2023-07-19 13:44
 * @description:
 **/
public interface IState {

  /**
   * 状态机是否已经开始
   */
  boolean isStarted();

  /**
   * 状态开始的时间
   */
  long getStartTime();

  /**
   * 返回状态的超时时间。
   */
  long getTimeout();

  /**
   * 返回状态码。
   */
  String getCode();

  /**
   * 重置状态
   */
  void reset();

  /**
   * 开始执行状态逻辑
   */
  void start();

  /**
   * 执行状态的初始化逻辑
   */
  void initialize(StateData data) throws Exception;

  /**
   * 初始化发生错误
   */
  void onInitializeError(Exception e, StateData data);

  /**
   * 刷新状态逻辑
   */
  void update(StateData data) throws Exception;

  /**
   * 刷新逻辑发生错误
   */
  void onUpdateError(Exception e, StateData data);

  /**
   * 状态执行结束了，返回下一个状态的code
   */
  String finish(StateData data) throws Exception;

  /**
   * 状态超时结束
   */
  String finishTimeout(StateData data);

  /**
   * 结束状态时发生错误
   */
  String onFinishError(boolean timeout, Exception e, StateData data);

}
