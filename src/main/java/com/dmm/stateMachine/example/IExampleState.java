package com.dmm.stateMachine.example;

import com.dmm.stateMachine.machine.IState;

/**
 * @author: zhangxun
 * @create: 2023-08-15 14:35
 * @description:
 **/
public interface IExampleState extends IState {
    void doProcessor(String desc);
}
