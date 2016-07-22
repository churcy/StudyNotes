# StudyNotes
study notes
It contains maven http redis nginx netty linux and so on

****************maven*******************
1.Download and install
https://maven.apache.org/

2.Build project

simple maven project
mvn archetype:create -DgroupId=////your groupId////// -DartifactId=///your artifactId//// -DpackageName=///your default packagename///

web maven project
mvn archetype:create -DgroupId=////your groupId////// -DartifactId=///your artifactId//// -DarchetypeArtifactId=maven-archetype-webapp

Description：archetypeArtifactId（the type of your project ）
* maven-archetype-archetype
* maven-archetype-j2ee-simple
* maven-archetype-mojo
* maven-archetype-portlet
* maven-archetype-profiles (currently under development)
* maven-archetype-quickstart
* maven-archetype-simple (currently under development)
* maven-archetype-site
* maven-archetype-site-simple
* maven-archetype-webapp

3.For multiple cooperating module

  it depends on your pom.xml,you can view it in this project

4.commond line

mvn clean
mvn install