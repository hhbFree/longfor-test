package design.staticFinal;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2021/2/4 17:23
 */
public class DemoCl{

    private  static final DemoIn d=(a,n)->{
        System.out.println(a+n);
    };
    protected void process()  {
        d.run("1",1);
    }


    public static void main(String[] args) {
        DemoCl demoCl=new DemoCl();

        demoCl.process();
    }

//    this.chain2.processModuleChain(ctx, new Action<ModuleState>() {
//        @Override
//        public void run(ModuleState a) {
//            if (!a.isTerminated()) {
//                try {
//                    chain.doFilter(request, response);
//                } catch (Exception ex) {
//                    LOGGER.error("doFilter failed {}", ex);
//                }
//            }
//        }
//    });
//
//    public void processModuleChain(ModuleContext ctx, Action<ModuleState> callback) {
//        ModuleChain header;
//        if (module == null) {
//            header = null;
//        } else {
//            header = this;
//        }
//        innerProcess(header, ctx, callback);
//    }
}
