package com.longfor.shopping.plugin.task.listener;

/**
 * Created by Coral on 7/26/15.
 */
public class BeanContextModification {
	public enum Action {
		INSERT,
		UPDATE,
		DELETE,
	}
	private Action action;
	private BeanContext beanContext;
	// private String reason;

	public BeanContextModification(Action action, BeanContext beanContext) {
		this.action = action;
		this.beanContext = beanContext;
	}


	public BeanContext getBeanContext() {
		return beanContext;
	}
	public Action getAction() {
		return action;
	}


}
