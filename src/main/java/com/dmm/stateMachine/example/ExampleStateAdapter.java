package com.dmm.stateMachine.example;

import com.dmm.stateMachine.machine.BaseState;
import com.dmm.stateMachine.machine.StateData;

/**
 * @author: zhangxun
 * @create: 2023-08-15 15:04
 * @description:
 **/
public abstract class ExampleStateAdapter extends BaseState implements IExampleState {
    @Override
    public void doProcessor(String desc) {
        System.out.println(desc + " into base adapter ...");
    }

    @Override
    public String onFinishError(boolean timeout, Exception e, StateData data) {
        if (timeout) {
            return "time out";
        }
        return e.getMessage();
    }
}
