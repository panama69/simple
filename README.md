This script has several unique things:
1. selecting last item in a list that grows - used to demonstrate to PwC
2. building jar artifact to execute test from command line
3. over-ride normal execution on localhost and port
4. pom.xml to handle profiles to run mvn with different execution locations

## PwC
For this to work you need to go to https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_table_insertrow
* copy the contents of the **w3schools** file (in this project) and past it in to their script area and hit run on the pane
* run the test with the "leanft-test" profile selected

## build jar artifact to run test from command line
* select build->build artifact->simple:jar->build
* the jar file is placed in the out->artifacts->simple_jar->simple.jar
* you can run the test and over-ride the localhost:5095 by using
  * java -cp simple.jar;junit-4.12.jar;hamcrest-core-1.3.jar -DserverAddres="ws://dockerserver:5096" org.junit.runner.JunitCore net.hpe.leanFtTest
  * this assumes all of the jars are in the same director and you have junit installed

## over-ride normal localhost execution
See above to do from command line
See below to see how to do from maven project

## pom.xml to handle different execution locations
* select leanft-test
* select which server to execute on dockerserver|dockerclient
* select the port for execution 5095|5096

This assumes you have the leanft remote agent is configured correctly <hpe install>\Tools\RemoteAgent\LFTRemoteAgent.exe.Config on Windows