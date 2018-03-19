package com.wanma.ims.common.domain;

import java.util.ArrayList;
import java.util.List;

public class NodeDO {
	private String id;
	private String name;
	private String target;
	private List<NodeDO> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<NodeDO> getChildren() {
		return children;
	}

	public void setChildren(List<NodeDO> children) {
		this.children = children;
	}
	
	public void addNode(NodeDO node){
		if(children==null){
			children=new ArrayList<NodeDO>();
		}
		children.add(node);
	}

	@Override
	public String toString() {
		return "NodeDO [id=" + id + ", name=" + name + ", target=" + target
				+ ", children=" + children + "]";
	}

}
