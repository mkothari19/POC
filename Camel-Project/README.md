# BDU API Client

## Getting Started

These instruction will enable you to build BDUAPIClient.

### Prerequisites

 
 * [Ant] (https://ant.apache.org/) - Build tool
 * [Java] (https://www.java.com/en/download/)
 * [MapR] (https://mapr.com/)
 
### Compiling

Below is the specific task for compiling the application

```
ant compile
```
 
### Packaging BDU API Client to executable Jar

Below is the step by step creating an executable jar

```
cd <BDU API Client source directory>
ant clean
ant compile
ant jar
```
or simple invoking jar task 

```
ant jar
```
Executable jar would be located at the following directory.

```
<BDU API Client source directory>/build/jar/BDUAPIClient.jar
```

### Deployment

## Bash Script

 * bduapiclient.sh - start-up script for BDUAPIClient
 * stream-publish - bash script for sending message to stream topic
 * file-stream-publish - bash script for sending file/files content to stream topic
