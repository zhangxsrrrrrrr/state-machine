package com.dmm;


import com.dmm.stateMachine.example.*;

import java.util.ArrayList;

/**
 * @author: zhangxun
 * @create: 2023-08-14 15:02
 * @description:
 **/
public class MainTestClass {
    public static void main(String[] args) {
        ExampleMachine exampleMachine = new ExampleMachine();
        ArrayList<IExampleState> iExampleStates = new ArrayList<>();
        iExampleStates.add(new StartState());
        iExampleStates.add(new CancelState());
        iExampleStates.add(new DisposedState());
        exampleMachine.setStates(iExampleStates);
        exampleMachine.start(StateType.START);
        while (true) {
            exampleMachine.update();
            if (exampleMachine.getCurrentStateCode() == null) {
                return;
            }

        }
    }
}
