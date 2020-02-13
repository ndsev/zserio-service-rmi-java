# Zserio Service RMI backend

Sample implementation of Zserio Service RMI backend in **Java**.

# Prerequisites

   1. Java SDK
   2. Zserio Java runtime library (`zserio_runtime.jar`)
   3. Zserio compiler (`zserio.jar`)

> Zserio prerequisites are included in this repo in 3rdparty folder.

# Usage

## Calculator Example

```bash
mkdir build
java -jar 3rdparty/zserio.jar \
     -src examples/zserio/service/rmi/examples/calculator calculator.zs -java build \
     -setTopLevelPackage zserio.service.rmi.examples.calculator.gen
javac -d build -cp 3rdparty/zserio_runtime.jar \
      src/zserio/service/rmi/*.java \
      examples/zserio/service/rmi/examples/calculator/*.java \
      build/zserio/service/rmi/examples/calculator/gen/calculator/*.java
java -cp 3rdparty/zserio_runtime.jar:build zserio.service.rmi.examples.calculator.CalculatorServer &
java -cp 3rdparty/zserio_runtime.jar:build zserio.service.rmi.examples.calculator.CalculatorClient
# follow client's instructions
# ...
# pres q + ENTER to quit the client
fg # and pres Ctrl+C to quit the server
```
