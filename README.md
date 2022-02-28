# Zserio Service RMI backend

Sample implementation of Zserio Service RMI backend in **Java**.

# Prerequisites

1. Java SDK
2. Apache Maven

# Usage

## Calculator Example

```bash
# download the latest zserio version
mvn dependency:copy -Dmaven.repo.local="build/download" \
        -Dartifact=io.github.ndsev:zserio:LATEST \
        -DoutputDirectory="build" -Dmdep.stripVersion=true

# download the latest zserio runtime version
mvn dependency:copy -Dmaven.repo.local="build/download" \
        -Dartifact=io.github.ndsev:zserio-runtime:LATEST \
        -DoutputDirectory="build" -Dmdep.stripVersion=true

# generate example using Zserio
java -jar build/zserio.jar \
     -src examples/zserio/service/rmi/examples/calculator calculator.zs -java build \
     -setTopLevelPackage rmi.examples.calculator.gen

# compile example
javac -d build -cp build/zserio-runtime.jar \
      src/zserio/service/rmi/*.java \
      examples/zserio/service/rmi/examples/calculator/*.java \
      build/rmi/examples/calculator/gen/calculator/*.java

# run example
java -cp build/zserio-runtime.jar:build zserio.service.rmi.examples.calculator.CalculatorServer &
java -cp build/zserio-runtime.jar:build zserio.service.rmi.examples.calculator.CalculatorClient
```
