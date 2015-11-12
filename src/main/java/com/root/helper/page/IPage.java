package com.root.helper.page;

import java.util.Collection;

public interface IPage<T> extends java.io.Serializable {

	int getTotal();
	int getSize();
	int getIndex();
	int getTotalRecord();
	Collection<T> getData();
}
