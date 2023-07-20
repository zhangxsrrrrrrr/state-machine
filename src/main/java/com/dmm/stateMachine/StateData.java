package com.dmm.stateMachine;

/**
 * @author: zhangxun
 * @create: 2023-07-19 13:46
 * @description:
 **/
public class StateData {

  public boolean needUpdate = true;

  public boolean finish = false;

  public int result = -1;

  public String errMsg = null;

  public void reset() {
    this.needUpdate = true;
    this.finish = false;
    this.result = -1;
    this.errMsg = null;
  }
}
