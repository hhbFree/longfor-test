//package proxy.cglib;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
////被代理的类即目标对象
//public class A {
//    public void execute(){
//        System.out.println("执行A的execute方法...");
//    }
//}
//
////代理类
//class CGLibProxy implements MethodInterceptor {
//
//    /**
//     * 被代理的目标类
//     */
//    private A target;
//
//    public CGLibProxy(A target) {
//        super();
//        this.target = target;
//    }
//
//    /**
//     * 创建代理对象
//     * @return
//     */
//    public A createProxy(){
//        // 使用CGLIB生成代理:
//        // 1.声明增强类实例,用于生产代理类
//        Enhancer enhancer = new Enhancer();
//        // 2.设置被代理类字节码，CGLIB根据字节码生成被代理类的子类
//        enhancer.setSuperclass(target.getClass());
//        // 3.//设置回调函数，即一个方法拦截
//        enhancer.setCallback(this);
//        // 4.创建代理:
//        return (A) enhancer.create();
//    }
//
//    /**
//     * 回调函数
//     * @param proxy 代理对象
//     * @param method 委托类方法
//     * @param args 方法参数
//     * @param methodProxy 每个被代理的方法都对应一个MethodProxy对象，
//     *                    methodProxy.invokeSuper方法最终调用委托类(目标类)的原始方法
//     * @return
//     * @throws Throwable
//     */
//    @Override
//    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//   //过滤不需要该业务的方法
//      if("execute".equals(method.getName())) {
//          //调用前验证权限(动态添加其他要执行业务)
//          System.out.println("调用前验证权限(动态添加其他要执行业务)");
//
//          //调用目标对象的方法(执行A对象即被代理对象的execute方法)
//          Object result = methodProxy.invokeSuper(proxy, args);
//
//          //记录日志数据(动态添加其他要执行业务)
//          System.out.println("记录日志数据(动态添加其他要执行业务)");
//
//          return result;
//      }else if("delete".equals(method.getName())){
//          //.....
//          return methodProxy.invokeSuper(proxy, args);
//      }
//      //如果不需要增强直接执行原方法
//      return methodProxy.invokeSuper(proxy, args);
//
//    }
//}