# Selenium Java framework for UI automation testing

## cucumber-java-junit
This project is created as a sample automation project for web

***

### Usage
You can run the tests by either executing mvn test or from run option of your IDE
You can set the values in Config.properties before running

***

#### Test  Parameters can be passed for Maven
browser = "firefox" || "chrome" || "ie" || "edge" || "opera" || "safari"

endpoint = "desktop" || "mobile" || "tablet" || "saucelabs" || "browserstack"

Browser size is set according to the Platform chosen and the values are taken from Config.properties

Example:
-Dbrowser=firefox 
-Dendpoint=tablet 

***

###Example Maven Commands : 

***

####For running regression set :
mvn test -Dcucumber.options="--tags @Regression" 

***

####For running a tag with other :
mvn test -Dcucumber.options="--tags @FirstTag and --tags @SecondTag" 

***

####For running a tag or another :
mvn test -Dcucumber.options="--tags @FirstTag,@SecondTag" 

***

####For running Regression tests on Desktop-Chrome :
mvn test -Dendpoint=desktop -Dbrowser=chrome -Dcucumber.options="--tags @Regression" 

***

####For running Smoke tests on Mobile-Firefox :
mvn test -Dendpoint=mobile -Dbrowser=Firefox -Dcucumber.options="--tags @Smoke" 

***

####For running SauceLabs and Browserstack:
All the parameters found in Config.properties can be send as maven command OR
Set the frequently used configuration in the config.properties file and run maven command as

-Dendpoint=browserstack

***

####For parallel runs on different endpoints:
Use the Makefile to configure the parallel runs and can be run through IDE OR
terminal using the below command

make clean_it set_end_point

***
