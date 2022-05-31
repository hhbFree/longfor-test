package design.Template;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2021/2/3 14:45
 */
public abstract class Person {

public boolean dealRun(){

    if(run()>20){
        return true;
    }
    return false;
}

public abstract int run();
}
