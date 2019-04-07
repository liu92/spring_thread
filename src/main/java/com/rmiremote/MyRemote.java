package com.rmiremote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * java rmi(远程方法调用)
 * @author
 */
public interface MyRemote extends Remote{

     String sayHello() throws RemoteException;
}
