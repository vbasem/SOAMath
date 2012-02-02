generate client side code :
    wsimport -d jax-ws/ http://192.168.56.1:8080/SOARegistry/       RegistryAndLookUpService?WSDL

generate server side code:
    wsgen -d build -s build/ -classpath build/ <package.class>
    
sample command to run addition server:
    java -classpath bin/ services.server.ArithmaticStandaloneServer --dhcp=192.168.0.1 --servicemode=addition --registerserver=192.168.56.1 --id=add1
