package zserio.service.rmi;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;
import java.rmi.server.UnicastRemoteObject;

import zserio.runtime.io.Writer;
import zserio.runtime.service.ServiceData;
import zserio.runtime.service.ServiceInterface;

/**
 * Implementation of the RMI ZserioService server.
 *
 * RMIServer creates a RMI server for a particular Zserio service implementation.
 */
public class RMIServer implements ZserioService
{
    /**
     * Constructor.
     *
     * @param serviceImpl     Implementation of a particular Zserio service.
     * @param serviceFullName Full name of the particular Zserio service.
     */
    public RMIServer(ServiceInterface serviceImpl, String serviceFullName)
    {
        this.serviceImpl = serviceImpl;
        this.serviceFullName = serviceFullName;
    }

    /**
     * Implementation of ZserioService RMI interface.
     */
    @Override
    public byte[] callMethod(String methodName, byte[] requestData) throws RemoteException
    {
        final ServiceData<?> serviceData = serviceImpl.callMethod(methodName, requestData, null);
        return serviceData.getByteArray();
    }

    /**
     * Starts the server on a default port.
     */
    public void start() throws RemoteException, AlreadyBoundException
    {
        start(DEFAULT_PORT);
    }

    /**
     * Starts the server on the specified port.
     *
     * @param port Port to use for the server.
     */
    public void start(int port) throws RemoteException, AlreadyBoundException
    {

        ZserioService zserioService = (ZserioService)UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind(serviceFullName, this);
    }

    private final static int DEFAULT_PORT = 1099; // default RMI port

    private final ServiceInterface serviceImpl;
    private final String serviceFullName;
}
