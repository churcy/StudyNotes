# StudyNotes
It contains maven http redis nginx netty linux and so on

###1.Download and install
[maven下载地址](https://maven.apache.org/) 
[mavenRepository下载地址](http://mvnrepository.com/)

###2.Build project

simple maven project
mvn archetype:create -DgroupId=////your groupId////// -DartifactId=///your artifactId//// -DpackageName=///your default packagename///

web maven project
mvn archetype:create -DgroupId=////your groupId////// -DartifactId=///your artifactId//// -DarchetypeArtifactId=maven-archetype-webapp

Description：archetypeArtifactId（the type of your project ）
*** maven-archetype-archetype
* maven-archetype-j2ee-simple
* maven-archetype-mojo
* maven-archetype-portlet
* maven-archetype-profiles (currently under development)
* maven-archetype-quickstart
* maven-archetype-simple (currently under development)
* maven-archetype-site
* maven-archetype-site-simple
* maven-archetype-webapp**

###3.For multiple cooperating module

  it depends on your pom.xml,you can view it in this project

###4.command line

**mvn** clean
**mvn** install

### linux防火墙
chkconfig  iptables  off
service iptables stop --停止
service iptables start --启动

centos7 

systemctl start firewalld.service#启动firewall
systemctl stop firewalld.service#停止firewall
systemctl disable firewalld.service#禁止firewall开机启动