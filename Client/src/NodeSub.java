// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 


public class NodeSub extends Node {

    public static int anInt1305;
    public NodeSub prevNodeSub;
    NodeSub nextNodeSub;
    public NodeSub() {
    }

    public final void unlinkSub() {
        if (nextNodeSub == null) {
        } else {
            nextNodeSub.prevNodeSub = prevNodeSub;
            prevNodeSub.nextNodeSub = nextNodeSub;
            prevNodeSub = null;
            nextNodeSub = null;
        }
    }
}
