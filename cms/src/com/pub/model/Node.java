package com.pub.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String id;
	private String name;
	private String target;
	private List<Node> children;

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

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	public void addNode(Node node){
		if(children==null){
			children=new ArrayList<Node>();
		}
		children.add(node);
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", target=" + target
				+ ", children=" + children + "]";
	}

}
