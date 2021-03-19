
test: DataWranglerTests.class BackEndDeveloperTests.class FrontEndDeveloperTests.class
	java -jar junit5.jar -cp . --scan-classpath

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

BackEndDeveloperTests.class: BackEndDeveloperTests.java
	javac -cp .:junit5.jar BackEndDeveloperTests.java

FrontEndDeveloperTests.class: FrontEndDeveloperTests.java Frontend.class Backend.class
	javac -cp .:junit5.jar FrontEndDeveloperTests.java

Frontend.class: Frontend.java FrontendInterface.class
	javac Frontend.java

Backend.class: Backend.java BackendInterface.class
	javac Backend.java

FrontendInterface.class: FrontendInterface.java
	javac FrontendInterface.java

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

clean: 
	$(RM) *.class
	$(RM) *~



