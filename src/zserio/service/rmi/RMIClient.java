package zserio.service.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import zserio.runtime.io.Writer;
import zserio.runtime.service.ServiceClientInterface;
import zserio.runtime.service.ServiceData;
import zserio.runtime.service.ServiceException;

/**
 * Implementation of RMI ZserioService client as Zserio generic service interface.
 */
public class RMIClient implements ServiceClientInterface
{
    /**
     * Constructor.
     *
     * @param serviceFullName Full name of the Zserio service.
     */
    public RMIClient(String serviceFullName) throws RemoteException, NotBoundException
    {
        this(serviceFullName, DEFAULT_HOST, DEFAULT_PORT);
    }

    /**
     * Constructor.
     *
     * @param serviceFullName Full name of the Zserio service.
     * @param host            Host to connect.
     */
    public RMIClient(String serviceFullName, String host) throws RemoteException, NotBoundException
    {
        this(serviceFullName, host, DEFAULT_PORT);
    }

    /**
     * Constructor.
     *
     * @param serviceFullName Full name of the Zserio service.
     * @param port            Port to connect.
     */
    public RMIClient(String serviceFullName, int port) throws RemoteException, NotBoundException
    {
        this(serviceFullName, DEFAULT_HOST, port);
    }

    /**
     * Constructor.
     *
     * @param serviceFullName Full name of the Zserio service.
     * @param host            Host to connect.
     * @param port            Port to connect.
     */
    public RMIClient(String serviceFullName, String host, int port) throws RemoteException, NotBoundException
    {
        Registry registry = LocateRegistry.getRegistry(host, port);
        zserioService = (ZserioService)registry.lookup(serviceFullName);
    }

    /**
     * Implementation of ServiceClientInterface::callMethod.
     */
    @Override
    public byte[] callMethod(String methodName, ServiceData<? extends Writer> request, Object context)
    {
        try
        {
            return zserioService.callMethod(methodName, request.getByteArray());
        }
        catch (RemoteException e)
        {
            throw new ServiceException("RMI call failed: " + e.getMessage());
        }
    }

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 1099;

    private final ZserioService zserioService;
}
