package com.qtu404.common;

import com.qtu404.beans.TwitterInfo;

import java.util.Comparator;

public class SortTwitterById implements Comparator<TwitterInfo> {

    public int compare(TwitterInfo o1, TwitterInfo o2) {
        // TODO Auto-generated method stub
        return (o2.getTwitterId()+"").compareTo((o1.getTwitterId()+""));
    }
}