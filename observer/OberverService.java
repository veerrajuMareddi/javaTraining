package com.rgt.onlinebidding.observer;

import com.rgt.onlinebidding.factory.Item;

public interface OberverService {
public void addObserver(Observer observer);
void notifyObserver(Item item);
}
