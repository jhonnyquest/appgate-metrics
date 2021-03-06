# Appgate-Metrics-Service
spring boot application that encapsulate all business logic of the Metric Service solution exposed as rest API

### environment variables
N/A (Coming soon)

### How to build project locally
in root folder execute `./gradlew clean build`

to run the server `./gradlew clean build bootrun`

### Server instance deployment diagram
![AWS Deployment Architecture Diagram](https://github.com/jhonnyquest/appgate-metrics/blob/master/AppGateMetricsEC2Instance-designer.png?raw=true)
Note that S3 service is deployed created to future use as file storage

### How to deploy project to Amazon AWS instance for the first time
* Create new AWS account or login into existing AWS account
* Create a new key pair to be able to access via SSH to server instance.
* Go to `Services -> CloudFormation -> Create Stack`
  * select `Prerequisite - Prepare template -> Template is ready` and `Specify template -> Upload a template file`
  * Chose file from project root:  [`appgate-metrics-cf-build.template`](appgate-metrics-cf-build.template)
* Create a new EC2 instance by selecting your proper instance configuration (Ubuntu server instance is recommended)
* Enable connections to server on port 8080 (or port configured to dispatch in application)
* Download the public key generated
* Install SSH key in your local computer to access new instance
* Login into server instance: `ssh -i YOUR_KEY.pem USERNAME@YOUR_SERVER_PUBLIC_DOMAIN_OR_IP`.
* Install Java in new AWS server: `sudo apt install default-jre`
* Copy .jar file to AWS server instance:
` scp -i YOUR_KEY.pem metrics-0.0.1-SNAPSHOT.jar USERNAME@YOUR_SERVER_PUBLIC_DOMAIN_OR_IP:SERVER_PATH`
* Run .jar into AWS server instance: `java -jar metrics-0.0.1-SNAPSHOT.jar`

### Runing instance
Project is currently running in the following instance

[`Instance running in AWS`](http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/)

### How to test?

 * To  confirm that App is running, you can try:[`test running endpoint`](http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/test)
it should return `test performed successfully...`
 * To load file you should perform the following CURL request in a CLI: `curl --location --request POST 'http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/logs' --form 'files=@"PATH_TO_YOUR_LOG_FILE"'` by replacing `PATH_TO_YOUR_LOG_FILE` 

Example:
`Jhonny@DESKTOP-KNKNLEU MSYS$` 
`curl --location --request POST 'http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/logs' --form 'files=@"sample_logs.txt"'`
`{"success":true,"message":"Your request was processed successfully","responseCode":"MTS-001"}`

* To get indicators information try the following endpoints:
  * Check isUserKnown endpoint: `http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/isUserKnown?username=USERNAME` -> replace USERNAME by proper one.
    EXMPLE: http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/isUserKnown?username=nikhil
  * Check isIpKnown endpoint: `http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/isIpKnown?ip=IP_ADDRESS` -> replace IP_ADDRESS by proper one.
    EXMPLE: http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/isIpKnown?ip=112.196.12.67
  * Check isIPInternal endpoint: `http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/isIPInternal?ip=IP_ADDRESS` -> replace IP_ADDRESS by proper one.
    EXMPLE: http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/isIPInternal?ip=112.196.12.67
  * Check lastSuccessfulLoginDate endpoint: `http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/lastSuccessfulLoginDate?username=USERNAME` -> replace USERNAME by proper one.
    EXMPLE: http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/lastSuccessfulLoginDate?username=cryptzone
  * Check lastSuccessfulLoginDate endpoint: `http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/lastFailedLoginDate?username=USERNAME` -> replace USERNAME by proper one.
    EXMPLE: http://ec2-100-25-223-172.compute-1.amazonaws.com:8080/metrics/lastFailedLoginDate?username=root
    
    
### notes

This project is a part of skills test for AppGate Hiring process, not for commercial use.