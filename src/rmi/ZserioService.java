package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ZserioService extends Remote
{
    byte[] callMethod(String methodName, byte[] requestData) throws RemoteException;
}
