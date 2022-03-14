package com.longfor.shopping.plugin.task.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Coral on 8/8/15.
 */
public class ServletRouter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletRouter.class);

	public ServletRouter(String protocol, BeanContextService contextService) {
		contextService.syncBeans(
				(sender, modification) -> {
					switch (modification.getAction()) {
						case INSERT:
							System.out.println(2);
							AbstractBeanContextService abstractBeanContextService=(AbstractBeanContextService)sender;
							Map<BeanIdentity, BeanContext> beans = abstractBeanContextService.getBeans();
							System.out.println("size:"+beans.size());
							//LOGGER.info("INSERT Servlet={}", modification.getBeanContext().getId());
//							addServlet((ServletContext) modification.getBeanContext());
							break;
						case UPDATE:
//							LOGGER.info("UPDATE Servlet={}", modification.getBeanContext().getId());
//							updateServlet((ServletContext) modification.getBeanContext());
							break;
						case DELETE:
//							LOGGER.info("REMOVE Servlet={}", modification.getBeanContext().getId());
//							removeServlet((ServletContext) modification.getBeanContext());
							break;
					}
				},
				bc -> {

					if (!bc.isLocal()) {
						System.out.println(3);
					}
//					if (!(bc instanceof ServletContext)) {
//						return false;
//					}
//					ServletContext sc = (ServletContext)bc;
//					if (!protocol.equals(sc.getProtocol())) {
//						return false;
//					}
//					if (sc.isLocal()) {
//						return TypeUtils.isTrue(sc.getConfiguration().getExport());
//					} else {
//						return true;
//					}
					return true;
				}
		);
	}


}