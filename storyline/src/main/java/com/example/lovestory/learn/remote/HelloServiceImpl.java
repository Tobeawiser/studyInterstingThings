package com.example.lovestory.learn.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{


    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     *
     * <p>The object is exported with a server socket
     *
     * @throws RemoteException if failed to export object
     * @since 1.1
     */
    protected HelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        String hello = "sayHello " + name;
        System.out.println(hello);
        return hello;
    }
}
