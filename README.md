ANZ toy robot code test
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
4. By default it will pickup input file here `src/main/resource/input.command` and write output file `output.result` into the folder where you run this app.

## Test
1. Simply run `./gradlew test` 
2. To check test coverage, run `./gradlew test jacocoTestReport`
3. Check this test report: [test report](./build/jacocoHtml/index.html)

## Integration test
1. run `./gradlew clean build -x integrationTest`

##Configuration
1. Input and output file are configurable here: `src/main/resource/application.yml
2. You can specify your input csv and out put csv by using `./gradlew bootRun -DGameInputFile=/yourInputFilePath -DGameOutputFile=/yourOutputfilepath`
3. If you don't specify absolute file path, it will read/write file from classpath
4. It will also output result into log.

##Important information here!!!!!!
1. App will discard invalid input like invalid commands or invalid move.

