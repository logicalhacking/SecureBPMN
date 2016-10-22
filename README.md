# SecureBPMN
[SecureBPMN](https://www.brucker.ch/projects/securebpmn/index.en.html)
is a domain-specific modeling language that allows to model security
aspects (e.g., access control, separation of duty,
confidentiality). SecurePBPMN is defined as a meta-model that can
easily be integrated into BPMN and, thus, can be used for modeling
secure and business processes as well as secure service compositions.

![ScreenShot of the SecureBPMN Modeling and Verification Environment] (https://www.brucker.ch/projects/securebpmn/img/activiti-bpmn-analysis.png)
The SecureBPMN tool chain does not only support modeling of secure business 
process and service compositions: it also supports the formal analysis both 
on the level of SecureBPMN models as well as refinement properties between 
the model and the actual implementation. 

## Installation
### SecureBPMN Designer
#### Prerequisites
* Eclipse Helios
* SATMC (http://www.ai-lab.it/satmc/), version 3.3.x
  (for the formal analysis of secure business processes)

#### Preparing the Eclipse environment
* First, install the GenericBreakGlass-XACML into your local 
  maven repository:
```
cd GenericBreakGlass-XACML/src/eu.aniketos.securebpmn.xacml.parent
mvn clean eclipse:clean 
mvn eclipse:eclipse 
mvn install 
cd ..
```
* To initialize the Eclipse project structure, please do 
```
cd designer/src//org.activiti.designer.parent
mvn clean eclipse:clean 
mvn eclipse:eclipse
cd ..
```
* After this, all projects can be imported into a fresh Eclipse
  workspace using `File -> Import -> Existing Projects into Workspace`.

#### Generate Model Classes
1. Open the folder `model` in the project `org.activiti.designer.model`
2. Open `BPMN20.genmodel`
3. Select the top level node (`bpmn2`)
4. Select `Generator -> Reload...` from the top-level menu, select
   `Ecore model` and complete the wizard. While doing this, ensure
   that all packages are select in the `Package Selection` screen.
5. Select the top level node (`bpmn2`)
6. Select `Generator -> Generate all` from the top-level menu

#### Start Eclipse Application
Select the project `org.activiti.designer.eclipse` and select `Run as
-> Eclipse application` in the context menu (right click).

### SecureBPMN Runtime
#### Prerequisites
Java 6 must be installed and executable:
```
export JAVA_HOME=<install directory of java 6>
export PATH=$JAVA_HOME/bin:$PATH
```
Moreover, the xalan libraries must be installed:
```
cd runtime/src/userguide 
ant install.xalan.libs
```
### Building the SecureBPMN Runtime
* If you did not install GenericBreakGlass-XACML into your local 
  maven repository as part of the installation of the SecureBPMN 
  Designer:
```
cd GenericBreakGlass-XACML/src/eu.aniketos.securebpmn.xacml.parent
mvn clean eclipse:clean 
mvn eclipse:eclipse 
mvn install 
cd ..
```
* Compile the SecureBPMN runtime:
```
cd runtime/src/distro
ant clean distro 
```
If ``ant `clean distro``` is not able to download tomcat, please 
download `apache-tomcat-6.0.32.zip` and copy it into 
`runtime/src/distro/target`.

### Executing the SecureBPMN Runtime
```
cd runtime/src/distro/target/activiti-5.8/setup/
ant demo.start 
```
And open `http://localhost:8080/activiti-explorer` in a web browser. 
Note that `and demo.stop` will stop the demo and `ant demo.clean` will 
reset the demo setup.

## Team 
Main developer: [Achim D. Brucker](http://www.brucker.ch/)

### Contributors
* Jan Alexander
* Matthias Klink
* Helmut Petritsch
* Raj Ruparel

## Publications
Related publications are listed on the [SecureBPMN 
website](https://www.brucker.ch/projects/securebpmn/index.en.html). 
The core publications are:
* Achim D. Brucker. [Integrating Security Aspects into Business Process
  Models](http://www.brucker.ch/bibliography/download/2013/brucker-securebpmn-2013.pdf).  
  In it - Information Technology, 55 (6), pages 239-246, 2013.
  doi:[10.1524/itit.2013.2004](http://dx.doi.org/10.1524/itit.2013.2004)
  http://www.brucker.ch/bibliography/abstract/brucker-securebpmn-2013
* Achim D. Brucker, Luca Compagna, and Pierre Guilleminot. [Compliance
  Validation of Secure Service Compositions](http://www.brucker.ch/bibliography/download/2014/brucker.ea-aniketos-compliance-2014.pdf). 
  In Secure and Trustworthy Service Composition: The Aniketos Approach. 
  Lecture Notes in Computer Science: State of the Art Surveys (8900), 
  pages 136-149, Springer-Verlag, 2014.
  doi:[10.1145/2295136.2295160](http://dx.doi.org/10.1145/2295136.2295160)
  http://www.brucker.ch/bibliography/abstract/brucker.ea-aniketos-compliance-2014
* Achim D. Brucker, Isabelle Hang, Gero Lückemeyer, and Raj
  Ruparel. [SecureBPMN: Modeling and Enforcing Access Control
  Requirements in Business Processes](http://www.brucker.ch/bibliography/download/2012/brucker.ea-securebpmn-2012.pdf). 
  In ACM symposium on access control models and technologies (SACMAT), 
  pages 123-126, ACM Press, 2012.
  doi:[10.1145/2295136.2295160](http://dx.doi.org/10.1145/2295136.2295160)
  http://www.brucker.ch/bibliography/abstract/brucker.ea-securebpmn-2012
