package com.qtu404.common;

import com.qtu404.beans.ReplyInfo;
import com.qtu404.beans.TwitterInfo;

import java.util.Comparator;

public class SortReById implements Comparator<ReplyInfo> {

    public int compare(ReplyInfo o1, ReplyInfo o2) {
        // TODO Auto-generated method stub
        return (o2.getReplyId()+"").compareTo((o1.getReplyId()+""));
    }
}