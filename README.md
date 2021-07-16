# AutomationFramework-Selenium BDD

This is web based UI automation framework created using Java cucumber selenium with maven.
All Dependencies and plugins are added to POM.xml
This is Page object model framework with Page Factory implementation.
Config related data is available in config.properties in "src/test/resources/config/config.properties"
The feature files are in the location: "src/test/resources/features"

To run the automation scripts there are multiple ways.
1.From Runner class(FlightTest.java) available in "src/test/java/TestRunner" as JUnit.
2.By maven command as :
	mvn clean compile
	mvn test
