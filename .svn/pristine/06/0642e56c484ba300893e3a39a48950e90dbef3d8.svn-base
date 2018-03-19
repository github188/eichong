package com.wanma.hbase;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

public class FilterBuilder {
	private String type;
	private String family;
	private String column;
	private String value;

	public FilterBuilder() {
	}
	
	public FilterBuilder(String type,  String value) {
		this.type = type;
		this.value = value;
	}
	public FilterBuilder(String type, String family,  String value) {
		this.type = type;
		this.family = family;
		this.value = value;
	}
	
	public FilterBuilder(String type, String family, String column, String value) {
		this.type = type;
		this.family = family;
		this.column = column;
		this.value = value;
	}

	public Filter buildFilter() {
		if (StringUtils.isBlank(type))
			return null;
		String condition =type;
		if (condition.equals(RealtimeConstant.FITER_EQUAL)) {
			SingleColumnValueFilter scvf = new SingleColumnValueFilter(
					Bytes.toBytes(family), Bytes.toBytes(column), CompareOp.EQUAL,
					new SubstringComparator(value));
			scvf.setFilterIfMissing(true);
			return scvf;
		} else if (condition.equals(RealtimeConstant.FITER_NOT_EQUAL)) {
			SingleColumnValueFilter scvf = new SingleColumnValueFilter(
					Bytes.toBytes(family), Bytes.toBytes(column),
					CompareOp.NOT_EQUAL, Bytes.toBytes(value));
			scvf.setFilterIfMissing(true);
			return scvf;
		} else if (condition.equals(RealtimeConstant.FITER_PREFIX)) {
			PrefixFilter fiter = new PrefixFilter(value.getBytes());
			return fiter;
		}  else {
			return null;
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
