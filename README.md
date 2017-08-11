ANZ code test
======
This is a simple spring boot app to simulate toy robot movement. Currently it only support CSV input and output.
Please check [requirement.md](./requirement.md) for requirements.

##Prerequirement
1. JAVA 8
2. Gradle 3.5
3. Maven

##Build

Windows:

`gradlew.bat build`

Linux/Mac:

`./gradlew build`


## Run
1. Simply run `./gradlew bootRun`
4. By default it will pickup csv here `src/main/resource/input.csv` and write output CSV into the folder where you run this app.

## Test
1. Simply run `./gradlew test` 
2. To check test coverage, run `./gradlew test jacocoTestReport`

##Integration test
1. `./gradlew clean build -x integrationTest`

##Configuration
1. Tax rate and input CSV file is configurable here: `src/main/resource/application.yml
2. You can specify your input csv and out put csv by using `./gradlew bootRun -DinputCSVFile=/youroutputcsvfilepath -DoutputCSVFile=/youroutputcsvfilepath`


