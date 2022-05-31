package design.listenter.demo3;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/5/21 18:31
 */
public class MyCallback {
   // MyAction action;
    public void setLister(int i, MyAction action){
        //this.action=action;
        i=i%3;//处理相关逻辑，在rcs中就是去请求别人
        switch (i){
            case 1:
                action.success(i+"");
                break;
            case 2:
                action.fail(i+"");
                break;
            case 3:
                action.timeout(i+"");
                break;
        }
    }
}
