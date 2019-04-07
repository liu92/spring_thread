package com.rmiremote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 远程服务实现类
 * @author
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    /**
     * 超类构造器
     *
     * @throws RemoteException
     */
    public MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Server  says ，'Hey'";
    }

    public static void main(String[] args) throws RemoteException {
        MyRemote  service = new MyRemoteImpl();
        try {
            Naming.bind("RemoteHello",service);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
