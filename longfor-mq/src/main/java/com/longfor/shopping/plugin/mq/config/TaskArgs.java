package com.longfor.shopping.plugin.mq.config;


public class TaskArgs  implements Comparable<TaskArgs> {

	private String id;
	private String event;
	private byte[] content;
	private String tag;
	private int priority;
	private long tid;

	private Object object;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public int compareTo(TaskArgs o) {
		if (priority < o.priority) {
			return -1;
		} else {
			if (priority > o.priority) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
