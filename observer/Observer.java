package com.rgt.onlinebidding.observer;

import com.rgt.onlinebidding.factory.Item;

public interface Observer {
	void update(Item item, Observer observer);
}
