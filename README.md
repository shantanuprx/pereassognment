Please follow below steps in order to use this microservice setup (This setup is strictly for Windows 11 and docker-desktop):-

1. Install docker desktop (WSL). Enable kubernetes desktop in the settings of docker desktop. Once docker engine and kubernetes is up and running. Verify with some command like 'kubectl get pods'/
2. Now we need to install ingress controller to inward the traffic inside the cluster. So run this command "kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.1/deploy/static/provider/cloud/deploy.yaml".
3. Once installation is completed, verify with "kubectl get pods --namespace=ingress-nginx". All pods should be up and running.
4. Now we need to setup mysql for this. Go to https://github.com/shantanuprx/pereassognment/tree/main/gatewayservice/gatewayservice and apply the "globalconfigmap.yml" and then "mysqldeployment.yml". Now mysql pods should be up and running. 
5. Try connecting to mysql service, either by going inside the pod or through a client like Mysql Workbench on localhost:3306. Username :- root , password = Mysql@123.
6. For distributed tracing we will be installing jaegar. For this just go to (https://github.com/shantanuprx/pereassognment/tree/main/gatewayservice/gatewayservice) and run jaegardeployment.bat. Check for pods of "jaegarcollectordeployment" pod, it should be up and running.
7. Now its time to setup the remaining microservices, for the ease of setup. One "deploy.bat" is included in each microservice. Edit that deploy.bat and replace "gryffindor937" with your own docker hub user.
8. Pre-requisite for running the deploy.bat are:-
   a. Maven setup should be available on the system.
   b. Docker client should be logged in docker hub.
   c. Jdk 17 should be available on the system.
9. Now start setting up the microservices in following order.
   a. Discoveryservice
   b. Configurationserver.
   c. Gatewayservice
   d. Productservice
   e. Userservice
   f. Orderservice.
   g. you can setup catalogserivce as well but implementation for elastic search is not available as of now
10. Start apispecservice in your local and check "http://localhost:9999/swagger-ui/index.html#/" for  api specs. Also you can import postman collection to make calls.

Note:- There is no direct registration for admin users as of now. So just register a user and update the role in DB as 'ADMIN', to make the user admin.
