package zserio.service.rmi.examples.calculator;

import java.math.BigInteger;
import java.lang.Math;

import zserio.service.rmi.RMIServer;

import rmi.examples.calculator.gen.calculator.Calculator;
import rmi.examples.calculator.gen.calculator.I32;
import rmi.examples.calculator.gen.calculator.U64;
import rmi.examples.calculator.gen.calculator.Double;

public class CalculatorServer
{
    public static void main(String[] args)
    {
        try
        {
            final CalculatorService calculatorService = new CalculatorService();
            // RMIServer from ZserioServiceRmi library uses the Zserio Calculator Service
            final RMIServer rmiServer = new RMIServer(calculatorService, calculatorService.serviceFullName());
            rmiServer.start();
        }
        catch (Exception e)
        {
            System.err.println("CalculatorServer error: " + e.getMessage());
        }
    }

    /** Implementation of Zserio Calculator Service. */
    private static class CalculatorService extends Calculator.CalculatorService
    {
        @Override
        protected U64 powerOfTwoImpl(I32 request, Object context)
        {
            final U64 response = new U64(BigInteger.valueOf(request.getValue()).pow(2));
            System.out.println("CalculatorServer: powerOfTwoImpl called, request=" + request.getValue() +
                    ", response=" + response.getValue() + ", context=" + context);
            return response;
        }

        @Override
        protected Double squareRootImpl(Double request, Object context)
        {
            final Double response = new Double(Math.sqrt(request.getValue()));
            System.out.println("CalculatorServer: squareRootImpl called, request=" + request.getValue() +
                    ", response=" + response.getValue() + ", context=" + context);
            return response;
        }
    }
}
