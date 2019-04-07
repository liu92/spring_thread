package com.rmiremote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 客户端
 * @author
 */
public class MyRemoteClient {
    public static void main(String[] args) {
        new MyRemoteClient().go();
    }

    public void  go(){
        try {
            MyRemote service = (MyRemote) Naming.lookup("127.0.0.1/RemoteHello");
            String s = service.sayHello();
            System.out.println(s);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
