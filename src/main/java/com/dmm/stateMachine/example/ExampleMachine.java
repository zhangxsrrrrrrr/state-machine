package com.dmm.stateMachine.example;

import com.dmm.stateMachine.machine.StateMachine;
import com.dmm.stateMachine.machine.StateWrapper;

/**
 * @author: zhangxun
 * @create: 2023-08-15 14:39
 * @description:
 **/
public class ExampleMachine extends StateMachine<IExampleState> {
    @Override
    public void update() {
        super.update();
        StateWrapper<IExampleState> currentStateWrapper = this.getCurrentStateWrapper();
        if (currentStateWrapper != null) {
            currentStateWrapper.state.doProcessor("zhangSan");
        }
        super.update();
    }
}
