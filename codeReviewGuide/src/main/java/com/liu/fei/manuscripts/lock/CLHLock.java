package com.liu.fei.manuscripts.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liufei
 * @since 2022/10/16
 **/
public class CLHLock implements Lock {

    AtomicReference<QNode> tail;

    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;


    public CLHLock() {

        tail = new AtomicReference<>(new QNode());

        myNode = ThreadLocal.withInitial(QNode::new);
        myPred = ThreadLocal.withInitial(() -> null);
    }

    @Override
    public void lock() {
        QNode qnode = myNode.get();

        qnode.locked = true;

        QNode pred = tail.getAndSet(qnode);

        myPred.set(pred);

        while (pred.locked) {

        }

    }


    @Override

    public void unlock() {

        QNode qnode = myNode.get();

        qnode.locked = false;
        myNode.set(myPred.get());
    }

    private static class QNode {

        boolean locked;
    }


}