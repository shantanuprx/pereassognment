mvn clean install -Dmaven.test.skip=true & docker build . -t gryffindor937/configurationservicedeployment:1.0  & docker push gryffindor937/configurationservicedeployment:1.0 & kubectl delete deployment configurationservicedeployment & kubectl apply -f deployment.yml 