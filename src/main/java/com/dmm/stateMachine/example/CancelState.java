package com.dmm.stateMachine.example;

import com.dmm.stateMachine.machine.StateData;

import java.util.Objects;

/**
 * @author: zhangxun
 * @create: 2023-08-15 15:09
 * @description:
 **/
public class CancelState extends ExampleStateAdapter {
    @Override
    public String getCode() {
        return StateType.CANCEL;
    }

    @Override
    public void initialize(StateData data) throws Exception {
        System.out.println("cancel 初始化一下");
        data.result = 2;
    }

    @Override
    public void update(StateData data) throws Exception {
        System.out.println("结束 ....");
        data.finish = true;
    }

    @Override
    public String finish(StateData data) throws Exception {
        return StateType.DISPOSED;
    }

    @Override
    public String finishTimeout(StateData data) {
        return "1";
    }
}
