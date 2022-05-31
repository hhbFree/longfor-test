package design.listenter.demo2;

/**
 * @Description: 测试代理
 * @Author: hbHao
 * @Date: 2020/5/20 10:30
 */
public class Test {

    public static void main(String[] args) {
     ModuleChain moduleChain=new ModuleChain();
        AbstractModuleContext abstractModuleContext=new AbstractModuleContext();
        moduleChain.processModuleChain(abstractModuleContext, new Action<ModuleState>() {
            @Override
            public void run(ModuleState a) {
                System.out.println("qwewe");
            }
        });
    }


}
