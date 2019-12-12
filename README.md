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
_-Dbrowser=firefox 
-Dendpoint=tablet_ 

***

**Example Maven Commands:**

***

**For running regression set:**
_mvn test -Dcucumber.options="--tags @Regression"_

***

**For running a tag with other:**
_mvn test -Dcucumber.options="--tags @FirstTag and --tags @SecondTag"_

***

**For running a tag or another:**
_mvn test -Dcucumber.options="--tags @FirstTag,@SecondTag"_

***

**For running Regression tests on Desktop-Chrome:**
_mvn test -Dendpoint=desktop -Dbrowser=chrome -Dcucumber.options="--tags @Regression"_ 

***

**For running Smoke tests on Mobile-Firefox:**
_mvn test -Dendpoint=mobile -Dbrowser=Firefox -Dcucumber.options="--tags @Smoke"_

***

**For running SauceLabs and Browserstack:**
All the parameters found in Config.properties can be send as maven command OR
Set the frequently used configuration in the config.properties file and run maven command as

_-Dendpoint=browserstack_

***

**For parallel runs on different endpoints:**
Use the Makefile to configure the parallel runs and can be run through IDE OR
terminal using the below command

_make clean_it set_end_point_

***
