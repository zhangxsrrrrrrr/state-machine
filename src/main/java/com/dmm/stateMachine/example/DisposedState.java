package com.dmm.stateMachine.example;

import com.dmm.stateMachine.machine.StateData;

/**
 * @author: zhangxun
 * @create: 2023-08-15 15:09
 * @description:
 **/
public class DisposedState extends ExampleStateAdapter {
    @Override
    public String getCode() {
        return StateType.DISPOSED;
    }

    @Override
    public void initialize(StateData data) throws Exception {
        System.out.println("disposed   初始化一下");
        data.result = 2;
    }

    @Override
    public void update(StateData data) throws Exception {
//        if (data.result == 2) {
//            System.out.println("提前结束了呗");
//            data.finish = true;
//            return;
//        }
        System.out.println("被销毁咯 ...");
        Thread.sleep(1000);
        data.finish = true;
    }

    @Override
    public String finish(StateData data) throws Exception {
        return null;
    }

    @Override
    public String finishTimeout(StateData data) {
        return "1";
    }
}
